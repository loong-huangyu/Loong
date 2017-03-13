package com.hy.loong.unit;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.hy.loong.pojo.ImTemporaryRecordset;



public class LuceneImUnit {
    //存放索引文件的位置，即索引库
	static String indexpath = "D:/mylucene/luceneIndex";
	//词法分析器
    static Analyzer analyzer = new StandardAnalyzer();
	
	/** 
     * 将即将检索的资源写入索引库 
     * @param writer 
     * @throws Exception 
     */  
    public void buildDocs(List<ImTemporaryRecordset> list) throws Exception {  
    	///本地文件存储
    	Directory directory=FSDirectory.open(Paths.get(indexpath));
        //内存存储：优点速度快，缺点程序退出数据就没了，所以记得程序退出时保存索引库，与FSDirectory结合使用
        //由于此处只暂时保存在内存中，程序退出时没进行索引库保存，因此在搜索时程序会报错
        //Directory directory=new RAMDirectory();
        //IndexWriterConfig，据官方文档介绍，是对indexWriter的配置，其中包含了两个参数，第一个是目前的版本，第二个是词法分析器Analyzer。最新5.0已经不需要版本号，3.x和4.x需要！
        IndexWriterConfig config = new IndexWriterConfig(analyzer);  
        IndexWriter writer = new IndexWriter(directory,config);  

        //得到数据资源 
        
        System.out.println("buildDocs()->总人数为 :"+list.size());  
        //存放数据
        for(ImTemporaryRecordset imRecordset :list){  
        	 //Document存放经过组织后的数据源，只有转换为Document对象才可以被索引和搜索到
            Document doc = new Document();//创建索引库的文档  
            doc.add(new TextField("imId",imRecordset.getImId(),Store.YES));  
            doc.add(new TextField("fromId",imRecordset.getFromId(),Store.YES));  
            doc.add(new TextField("groupId",imRecordset.getGroupId(),Store.YES));  
            doc.add(new TextField("content",imRecordset.getContent(),Store.YES));  
            doc.add(new TextField("sendTime",String.valueOf(imRecordset.getSendTime()),Store.YES));  
            //将文档写入索引库
            writer.addDocument(doc);  
        }  
        
        int count =writer.numDocs();  
        //关闭IndexWriter,提交创建内容
        writer.close();  
        System.out.println("buildDocs()->存入索引库的数量："+count);  
    }  
    
    /**
     * 清空索引
     * @throws IOException 
     * 
     */
    public void delDocs() throws IOException{
    	Directory directory=FSDirectory.open(Paths.get(indexpath));
    	 IndexWriterConfig config = new IndexWriterConfig(analyzer);  
         IndexWriter writer = new IndexWriter(directory,config);  
         //清空索引库里已存在的文档（document）  
         writer.deleteAll();
         writer.close(); 
    }
    
    /** 
     * 从索引库中搜索你要查询的数据 
     * @param searcher 
     * @throws IOException 
     */  
    public List<ImTemporaryRecordset> searcherDocs(String fromId,String groupId) throws IOException{  
    	Directory directory=FSDirectory.open(Paths.get(indexpath));
    	IndexReader reader = DirectoryReader.open(directory);  
    	IndexSearcher searcher =new IndexSearcher(reader); 
    	List<ImTemporaryRecordset> recordsets = new ArrayList<ImTemporaryRecordset>();
    	ImTemporaryRecordset  recordset = new ImTemporaryRecordset();
    	
    	//单个搜索写法，多个搜索，因为BooleanQuery 5.X不能用，暂时没有好的解决方案
/*        TermQuery query =new TermQuery(new Term("fromId", fromId));  
        TermQuery query1 =new TermQuery(new Term("groupId", groupId));  
        TopDocs docs =searcher.search(query, 100);//查找  
*/        
    	
    	 Sort sort = new Sort();
         sort.setSort(new SortField("imId",SortField.Type.DOC,true));//true为降序,false为升序
    	
    	
        ScoreDoc[] scoreDocs;
        if(groupId!=null && groupId.length()>0){
        	scoreDocs =searcher.search(new TermQuery(new Term("groupId", groupId)),100,sort).scoreDocs;
        }else {
        	scoreDocs =searcher.search(new TermQuery(new Term("fromId", groupId)),100,sort).scoreDocs;
		}
        for(ScoreDoc doc:scoreDocs){//获取查找的文档的属性数据  
            int docID=doc.doc;  
            Document document =searcher.doc(docID);  
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
				recordset = new ImTemporaryRecordset(document.get("imId"),document.get("fromId"),
						document.get("groupId"),dateFormat.parse(document.get("sendTime")),document.get("content"));
				recordsets.add(recordset);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 
        return recordsets;
    } 
}
