/**    
 * 文件名：GetPostgisData.java    
 *    
 * 版本信息：    
 * 日期：2018年4月3日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package MapFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.data.FeatureStore;
import org.geotools.data.FeatureWriter;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.geometry.iso.io.wkt.WKTReader;
import org.opengis.feature.Feature;
import org.opengis.feature.GeometryAttribute;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeType;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.coordinate.LineString;

/**    
 *     
 * 项目名称：TwoMap    
 * 类名称：GetPostgisData    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2018年4月3日 下午1:28:23    
 * 修改人：jinyu    
 * 修改时间：2018年4月3日 下午1:28:23    
 * 修改备注：    
 * @version     
 *     
 */
public class GetPostgisData {
    static DataStore dbdataStore=null;
    //static PostgisNGDataStoreFactory factory=new PostgisNGDataStoreFactory(); 
    static SimpleFeatureStore fsBC=null; 
    @SuppressWarnings({ "unchecked", "unused" }) 
    private static void ConnPostGis(String dbtype,String URL,int port,String database, 
        String user,String password){ 
        Map<String,Object> params = new HashMap<String,Object>(); 
        params.put("dbtype", "postgis"); 
        params.put("host", URL); 
        params.put("port", new Integer(port)); 
        params.put("database", database); 
        params.put("user", user); 
        params.put("passwd", password);   
        //比较通用的
        try {
             dbdataStore=DataStoreFinder.getDataStore(params);
        } catch (IOException e) {
         
            e.printStackTrace();
        }
    
    } 

    //读取指定类型名的地理特征 
    public static void getFeatureSource(String sourceName){ 
        try { 
            fsBC = (SimpleFeatureStore) dbdataStore.getFeatureSource(sourceName);
        } catch (IOException e) { 
            e.printStackTrace(); 
        }        
    } 
    //取得POSTGIS中所有的地理图层 
    public static List<String> getAllLayers(){ 
        
     List<String> list=new ArrayList<String>();
        try { 
            String[] typeName = dbdataStore.getTypeNames(); 
            for(int i=0;i<typeName.length;i++){ 
                list.add(typeName[i]);
            } 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
        return list;
    } 
    //获取图层地理元素属性（Feature Attribute） 
    public static void getAttribute(){ 
        if(fsBC==null)
        {
            
        }
         SimpleFeatureType ftBC = fsBC.getSchema();
        for (int i = 0; i < ftBC.getAttributeCount(); i++) { 
            AttributeType at = ftBC.getType(i);
            //判断属性类型是否为可分配的几何对象 
            if (!Geometry.class.isAssignableFrom(at.getClass())) 
            {
                
            }
        } 
        System.out.println(); 
        for (int i = 0; i < ftBC.getAttributeCount(); i++) { 
            AttributeType at = ftBC.getType(i); 
            if (!Geometry.class.isAssignableFrom(at.getClass()))
                System.out.print(at.getName() + "\t"); 
        } 
    } 
    
    //从数据容器中读取所有的特征属性 
    @SuppressWarnings("deprecation") 
    public static void PostGisReading(){ 
        try { 
            SimpleFeatureCollection fsRU = fsBC.getFeatures(); 
            SimpleFeatureIterator reader = fsRU.features();
            
            while (reader.hasNext()) { 
                Feature feature; 
              
                    feature = reader.next(); 
                    feature.getUserData();
                    
            } 
           
        } catch (IOException e1) { 
            e1.printStackTrace(); 
        } 
    } 
    
    //添加特征值到新的特征对象中。等同于新建一个postgis数据表并向其中插入数据 
    @SuppressWarnings("deprecation") 
    public static void createFeatures(){ 
//        try { 
//            AttributeType geom = AttributeTypeFactory.newAttributeType("the_geom",LineString.class); 
//            AttributeType name = AttributeTypeFactory.newAttributeType("name",String.class); 
//            FeatureType ftRoad = FeatureTypeFactory.newFeatureType 
//                                (new AttributeType[] {geom,name}, "tem_road"); 
//            WKTReader wktReader = new WKTReader(); 
//            try { 
//                LineString geometry = (LineString) wktReader.read("LINESTRING (0 0, 10 10)"); 
//                String roadName="武络路"; 
//                pgDatastore.createSchema(ftRoad); 
//                FeatureWriter aWriter = pgDatastore.getFeatureWriter("tem_road", 
//                        ((FeatureStore) pgDatastore.getFeatureSource("tem_road")).getTransaction()); 
//                /** *//**如有批量导入数据要求，可使用 org.geotools.data.FeatureStore */ 
//                Feature aNewFeature = aWriter.next(); 
//               // aNewFeature.setAttribute("the_geom",geometry); 
//                //aNewFeature.setAttribute("name", roadName); 
//                aWriter.write(); 
//                aWriter.close(); 
//            } catch (Exception e) { 
//               
//        }
    } 
    
    //添加Feature到已知的图层之中 
    public static void insertFeatures(String featurename){ 
        WKTReader wktReader = new WKTReader(null); 
        try { 
            LineString geometry = (LineString) wktReader.read("LINESTRING (10 10, 20 20)"); 
            String roadName="珞瑜路"; 
            FeatureSource source = dbdataStore.getFeatureSource(featurename); 
            FeatureWriter aWriter = dbdataStore.getFeatureWriterAppend(featurename,((FeatureStore) source).getTransaction()); 
            /** *//**如有批量导入数据要求，可使用 org.geotools.data.FeatureStore */ 
            Feature feature = aWriter.next(); 
            try { 
                GeometryAttribute arg0 = null ;
                feature.setDefaultGeometryProperty(arg0);
                //feature.setAttribute("the_geom",geometry); 
               // feature.setAttribute("name", roadName); 
            } catch (Exception e) { 
              
                e.printStackTrace(); 
            } 
            aWriter.write(); 
            aWriter.close(); 
        } catch(Exception ex)
        {
            
        }
}
}
