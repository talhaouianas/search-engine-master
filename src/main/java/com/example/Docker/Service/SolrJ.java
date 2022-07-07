package com.example.Docker.Service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolrJ {
    private String query="    ";
    public String getQuery() {return query;}
    public void setQuery(String query) {this.query = query;}


    private static final String SOLR_CORE_URL = "http://localhost:8983/solr/mr_core1";

    private static final SolrClient solrClient = getSolrClient();

    public static void main(String[] args) {
        	List<Article> list = new ArrayList<Article>();
        System.out.println("========================== SolrJ_running ==========================");

        //si vous vouler reindexer dans un core
        SolrJ solrJ = new SolrJ();

          //indexingByUsingJavaObjectBinding("src/main/resources/Donnees");


      printResults(solrJ.queryingByUsingSolrQuery());
    }

    private static SolrClient getSolrClient() {
        return new HttpSolrClient.Builder(SOLR_CORE_URL).withConnectionTimeout(5000).withSocketTimeout(3000).build();
    }

    private static List<Article> getArticles(String url) {
        List<Article> articles = new ArrayList<Article>();

        try {
            Article.doTikaDocuments(new File(url),articles);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return articles;
    }

    public static void indexingByUsingJavaObjectBinding(String url) {
        try {
            List<Article> articles = getArticles( url );
            System.out.printf("Indexing %d articles...\n", articles.size());
            // send articles to Solr
            solrClient.addBeans(articles);

            // explicit commit pending documents for indexing
            solrClient.commit();

            System.out.printf("%d articles indexed.\n", articles.size());
        } catch (SolrServerException | IOException e) {
            System.err.printf("\nFailed to indexing articles: %s", e.getMessage());
        }
    }

    public SolrDocumentList queryingByUsingSolrQuery() {
        String value = this.query;
        System.out.println("Querying by using SolrQuery...");
        String lemme = null;
		// constructs a SolrQuery instance
        final SolrQuery solrQuery = new SolrQuery("title:*"+value+"*|| content:*"+value+"* || language:*"+value +"* || author:*"+value+"*");

        solrQuery.addField("title");
        solrQuery.addField("author");
        solrQuery.addField("language");
        solrQuery.addField("content");
      //  solrQuery.setSort("id", ORDER.asc);
        solrQuery.setRows(100);

        // sends search request and gets the response
        QueryResponse response = null;
        try {
            response = solrClient.query(solrQuery);
        } catch (SolrServerException | IOException e) {
            System.err.printf("\nFailed to search articles: %s", e.getMessage());
        }

        // print results to stdout
        if (response != null) return(response.getResults());
        else return null;
    }

    public static List<Article> printResults(SolrDocumentList documents) {
        List<Article> list = new ArrayList<Article>();
        System.out.printf("Found %d documents\n", documents.getNumFound());

        for (SolrDocument document : documents) {

            final String title = (String) document.getFirstValue("title");
            final String author = (String) document.getFirstValue("author");
            final String language = (String) document.getFirstValue("language");
            final String content = (String) document.getFirstValue("content");
            Article article = new Article(title,author,language,content);
            list.add(article);
            /*
            System.out.println("********************************************");
            System.out.printf(" title=%s, author=%s,language=%s,content=%s\n", title, author,language,content);
            */

        }
        return list;
    }
}
