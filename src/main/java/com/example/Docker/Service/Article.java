package com.example.Docker.Service;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.tika.langdetect.optimaize.OptimaizeLangDetector;
import org.apache.tika.language.detect.LanguageDetector;
import org.apache.tika.language.detect.LanguageResult;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class Article {

	@Field
    private String title;
	@Field
    private String author;
	@Field
    private String content;
    @Field
    private String language;

    @Override
	public String toString() {
		return "Article [title=" + title + ", author=" + author + ", content=" + content + ", language=" + language +"]";}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {this.author = author;}

    public Article() {}
	public Article(String title, String author, String language,String content ) {
		super();
		this.title = title;
		this.author = author;
		this.content = content;
		this.language = language;
	
	}




    private static void log(String msg) {
    	System.out.println(msg);
    }

	private void dumpMetadata(String fileName, Metadata metadata) {
        log("Dumping metadata for file: " + fileName);
        for (String name : metadata.names()) {
            log(name + ":" + metadata.get(name));
        }
        log("nn");
    }
	
	// Recursively traverse the filesystem, parsing everything found.
    static  List<Article> doTikaDocuments(File root,List<Article> list) throws IOException, SolrServerException {

        for (File file : root.listFiles()) {
            if (file.isDirectory()) {
                doTikaDocuments(file, list);
                continue;
            }
             String title;
             String author;
             String content;
             String language;
  
             AutoDetectParser autoParser = new AutoDetectParser();

            ContentHandler textHandler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            ParseContext context = new ParseContext();

            InputStream input = new FileInputStream(file);

            try {
                autoParser.parse(input, textHandler, metadata, context);
            } catch (Exception e) {
                // Needs better logging of what went wrong in order to
                // track down "bad" documents.
                log(String.format("File %s failed", file.getCanonicalPath()));
                e.printStackTrace();
                continue;
            }
            
            title = file.getName();
            author = metadata.get("Author");
              if (author != null) {author ="author";}
            content= textHandler.toString() ;
            language=lang(content);
           
          Article article= new Article(title, author, language,content);
          list.add(article);
        }
		return list;
    }

    public static String lang(String content) {
        LanguageDetector object = new OptimaizeLangDetector().loadModels();
        LanguageResult result = object.detect(content);
    return result.getLanguage();
    }
   
  /* String path = " "; 
    String lemme= " ";  
    String exp2 ="<lemma>" ; 
   
			       if(langue.equals("ARABE")) {
			    	 //  System.out.println("path est : "+files[i].getCanonicalPath()); 
			    	//   net.oujda_nlp_team.ADATAnalyzer.getInstance().processLemmatization(getCanonicalPath(),"utf−8",,"utf−8" ) ;
			    	   path = "File"+filecount+".xml" ; 
			    	   net.oujda_nlp_team.ADATAnalyzer.getInstance().processLemmatization(files[i].getPath(), "UTF-8" ,path, "UTF-8");
			    	      
			    	   File fi =  new File(path); 
			              BufferedReader ff  = new BufferedReader(new FileReader(fi));   
			    	   String str = ""; 
			    	   int indice1 = 0; 
			           int indice2 = 0;
			    	   while((str=ff.readLine())!= null)
					       {
			    		   if (str.contains(exp2) == true ) 
		                    {
		                    indice1 = str.indexOf("<lemma>") ;
		                    indice2 = str.indexOf("</lemma>");
		                   lemme +=" "+str.substring(indice1+"<lemma>".length(), indice2);
		                    }
					    	   
					       }
			       }*/
    
	 //Cette méthode permette de récupérer les données qui sont stocker dans solr 
	  
	/* public static List<String> test_query(String value) throws IOException{
		 	String mot = ""; 
		 // lemme 
		 	mot += value ; 
		 	
            String lemme= "";  
            String exp2 ="<lemma>" ; 
		       File f = new File("mon-fichier.txt");
		        PrintWriter writer = new PrintWriter("mon-fichier.txt", "UTF-8");
		        writer.println(mot);
		           writer.close();
		 	
		           net.oujda_nlp_team.ADATAnalyzer.getInstance().processLemmatization("mon-fichier.txt", "UTF-8" , "FileFile.xml", "UTF-8");

		           
		           File fi =  new File("FileFile.xml"); 
		              BufferedReader ff  = new BufferedReader(new FileReader(fi));   
		    	   String str = ""; 
		    	   int indice1 = 0; 
		           int indice2 = 0;
		    	   while((str=ff.readLine())!= null)
				       {
		    		   if (str.contains(exp2) == true ) 
	                    {
	                    indice1 = str.indexOf("<lemma>") ;
	                    indice2 = str.indexOf("</lemma>");
	                   lemme +=" "+str.substring(indice1+"<lemma>".length(), indice2);
	                    }
				    	   
				       }
		    	   System.out.println("lemme est : "+lemme); 

		 
		 
		 // lemme 
		    	   
SolrClient solrclient = new Http2SolrClient.Builder(URL).build() ; 
 List<String> ar=new ArrayList();
final Map<String, String> query = new HashMap<String , String>() ;
final Map<String, String> queryLemee = new HashMap<String , String>() ;
String res= "";

//query.put("q","id_fichier:"+value+"|| content:"+value+" || lang:"+value +" || Content-Type:"+value+"");// q est query doit ajouter : et * cad touts 
query.put("q","id_fichier:"+value+"|| contenu:"+value+"|| Name_:"+value+" || langue:"+value +" || Content-Type:"+value+" || lemme:"+lemme+"");// q est query doit ajouter : et * cad touts 
//queryLemee.put("q","lemme:"+lemme+"");// recherche par lemme juste dans les lemme 


MapSolrParams solrQuery = new MapSolrParams(query) ; // pour execution 
//MapSolrParams solrQueryLemme = new MapSolrParams(queryLemee) ; // pour execution 
QueryResponse response = null ;
//QueryResponse responseLemme = null ;
try{
    response = solrclient.query(solrQuery) ;
 //   responseLemme= solrclient.query(solrQueryLemme); 
}catch(SolrServerException | IOException e){
    System.err.printf("Failed to search : %s", e.getMessage());
}

if(response!=null){
    int cmp=0;
    // pour prendre out les resultat.
    for(SolrDocument docm: response.getResults()){
   	 
        final String path = (String) docm.getFirstValue("id_fichier");
        final String Name = (String) docm.getFirstValue("Name_");
        final String lang = (String) docm.getFirstValue("langue");
        final String Content_Type = (String) docm.getFirstValue("Content-Type");
        final String content = (String) docm.getFirstValue("contenu") ;
        final String leme = (String) docm.getFirstValue("lemme") ;
        
       
        
     /*   res+="<h3>Resultat : "+(cmp++)+" || id :<a href='"+path+"' >"+path+"</a> </h3>|| <h2>lang: "+lang+" ||"
                + " </h2>Content_Type: "+Content_Type+" ||<p> content: "+content+"</p>||<p> Name :"+ Name+"</p>\n<br>" ;*/
        
        
     /*   String requet =""; 
        requet += "["+path +"*"+content+"*"+leme+"*"+Name+"*"+Content_Type+"*"+lang+"* ] \n"; 
        ar.add(requet);// laliste prendre just path mais doit ajouter les autre 
        
    }*/
    
/* for(SolrDocument docm: responseLemme.getResults()){
	 final String lemmee = (String) docm.getFirstValue("lemme") ;
        
        
        ar.add(lemmee);// laliste prendre just path mais doit ajouter les autre 
            
  
}

    return ar;
}*/
    
			       
}

