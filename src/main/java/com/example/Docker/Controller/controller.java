package com.example.Docker.Controller;

import com.example.Docker.Service.Article;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Docker.Service.SolrJ;

import java.io.File;
import java.io.IOException;
import java.util.List;

@org.springframework.stereotype.Controller
public class controller {



@GetMapping("/home")
public String ViewForm(Model model) throws IOException {
    model.addAttribute("query",new SolrJ());

    return "index";
}

    @PostMapping("/controller")
    public String PostForm(@ModelAttribute SolrJ solrJ, BindingResult result, Model model) throws IOException {

        model.addAttribute("solrJ",solrJ.getQuery());
        SolrDocumentList documents=solrJ.queryingByUsingSolrQuery();
        List<Article> list = solrJ.printResults(documents);

        model.addAttribute("list",list);
        model.addAttribute("nbrdocs",documents.getNumFound());
        return "result";
    }


/*
@GetMapping(value="/Lang")//localhost:8090/Lang
public void  Langage() throws FileNotFoundException, IOException, SAXException, TikaException, SolrServerException {
	
	    fichier.list(); 
	    System.out.println(fichier.dircount);
	     System.out.println(fichier.filecount);
	
}


@GetMapping("/req/{value}/{n}")//localhost:8090/req
public List<String> getString(@PathVariable("value") String value,@PathVariable("n") int n) throws IOException, TikaException, SAXException{
	List list=new ArrayList();

	List<String> ar=fichier.test_query(value);
	for(int i=0;i<n;i++)
		list.add(ar.get(i));
	return list;
  }*/
    
    
    
}
