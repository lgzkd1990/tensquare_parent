import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * MongoDb入门小demo
 */
public class TestMongoDemo {
    public static void main(String[] args) {
        //insertdocs();
      // findall();
      findByCondition();
    }

    private static MongoCollection getConnections() {
        MongoClient client = new MongoClient("192.168.26.130");//创建连接
        MongoDatabase spitdb = client.getDatabase("spitdb");//打开数据库
        MongoCollection<Document> spit = spitdb.getCollection("spit");//获取集合
        return spit;
    }

    private static void insertdocs() {
        MongoCollection<Document> spit = getConnections();
        Map<String,Object> map=new HashMap();
        map.put("content","我要吐槽");
        map.put("userid","9999");
        map.put("visits",123);
        map.put("publishtime",new Date());
        Document document=new Document(map);
        spit.insertOne(document);//插入数据
        //client.close();
    }

    private static void findall() {
        MongoCollection<Document> spit = getConnections();
        FindIterable<Document> documents = spit.find();//查询记录获取文档集合
        for (Document document : documents) { //
            System.out.println("内容：" + document.getString("content"));
            System.out.println("用户ID:" + document.getString("userid"));
            System.out.println("浏览量：" + document.getInteger("visits"));
        }
        //client.close();//关闭连接
    }

    private static void findByCondition() {
        MongoCollection<Document> spit = getConnections();
        BasicDBObject bson = new BasicDBObject("visits", new BasicDBObject("$gt", 1000));// 构建查询条件
        FindIterable<Document> documents = spit.find(bson);//查询记录获取结果集合
        for (Document document : documents) { //
            System.out.println("内容：" + document.getString("content"));
            System.out.println("用户ID:" + document.getString("userid"));
            System.out.println("浏览量：" + document.getInteger("visits"));
        }
    }
}
