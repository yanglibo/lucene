package com.yanglibo.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {

        /******************************索引创建************************************/
        //索引所在的目录
        Directory directory = FSDirectory.open(Paths.get("c:\\lucene\\testindex"));
        //创建分词器
        Analyzer analyzer = new SimpleAnalyzer();
        //配置创建索引的规则
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        IndexUtil.indexDocs(indexWriter, Paths.get("c:\\lucene\\test"));

        /******************************索引查找************************************/
        //索引查找
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //创建查询条件
        QueryParser queryParser = new QueryParser("contents", analyzer);
        Query query = queryParser.parse("yanglibo1");
        IndexUtil.searchIndex(indexSearcher, query);

    }
}
