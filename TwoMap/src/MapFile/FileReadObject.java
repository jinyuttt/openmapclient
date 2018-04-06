package MapFile;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.dbf.DbaseFileHeader;
import org.geotools.data.shapefile.dbf.DbaseFileReader;
import org.geotools.data.shapefile.files.ShpFiles;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;



/**    
 *     
 * 项目名称：TwoMap    
 * 类名称：FileRead    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2018年4月2日 上午1:27:44    
 * 修改人：jinyu    
 * 修改时间：2018年4月2日 上午1:27:44    
 * 修改备注：    
 * @version     
 *     
 */
public class FileReadObject {
    public List<Map<String,Object>> readFile(String path) { 
        
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>(); 
        try{  
            File file = new File (path);  
            FileDataStore store = FileDataStoreFinder.getDataStore(file); 
            //设置字符编码  
            SimpleFeatureSource featureSource = store.getFeatureSource();
            SimpleFeatureCollection result = featureSource.getFeatures();  
            SimpleFeatureIterator itertor = result.features();  
            while (itertor.hasNext())  
            {  
                Map<String,Object> data  = new HashMap<String, Object>();  
                SimpleFeature feature = itertor.next();  
                Collection<Property> p = feature.getProperties();  
                Iterator<Property> it = p.iterator();  
                while(it.hasNext()) {  
                    Property pro = it.next();  
                    String field = pro.getName().toString();  
                    String value = pro.getValue().toString();  
                    field = field.equals("the_geom")?"wkt":field;  
                    data.put(field, value);  
                }  
                list.add(data);  
            }
        }
            catch(Exception ex)
            {
                
            }
            return list;
    }  
 @SuppressWarnings("deprecation")
public List<Map<String,Object>> readSHP(String shpPath) { 
        
     ShapefileDataStore shpDataStore = null;  
     List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();  
     try{  
         File file = new File (shpPath); 
         shpDataStore = new ShapefileDataStore(file.toURL());  
         //设置字符编码  
         Charset charset = Charset.forName("GBK");  
         shpDataStore.setCharset(charset);  
         String typeName = shpDataStore.getTypeNames()[0];  
         SimpleFeatureSource featureSource = null;  
         featureSource =  shpDataStore.getFeatureSource (typeName);  
         SimpleFeatureCollection result = featureSource.getFeatures();  
         SimpleFeatureIterator itertor = result.features();  
         while (itertor.hasNext())  
         {  
             Map<String,Object> data  = new HashMap<String, Object>();  
             SimpleFeature feature = itertor.next();  
             Collection<Property> p = feature.getProperties();  
             Iterator<Property> it = p.iterator();  
             while(it.hasNext()) {  
                 Property pro = it.next();  
                 String field = pro.getName().toString();  
                 String value = pro.getValue().toString();  
                 field = field.equals("the_geom")?"wkt":field;  
                 data.put(field, value);  
             }  
             list.add(data);  
         } 
     }
     catch(Exception ex)
     {
         
     }
     return list;
    }  
    
    
    public Map<String,Object> readDBF(String path) {  
        DbaseFileReader reader = null; 
        Map<String,Object> map=new HashMap<String,Object>();
        try {  
            reader = new DbaseFileReader(new ShpFiles(path), false,  
                    Charset.forName("GBK"));  
            DbaseFileHeader header = reader.getHeader();  
            int numFields = header.getNumFields();  
            for (int i = 0; i < numFields; i++) {  
                header.getFieldName(i);  
                header.getFieldType(i);// 'C','N'  
                header.getFieldLength(i);  
            }  
      
            // 迭代读取记录  
            while (reader.hasNext()) {  
                try {  
                    Object[] entry = reader.readEntry();  
                    for (int i = 0; i < numFields; i++) {  
                        String title = header.getFieldName(i);  
                        Object value = entry[i];  
                       // String name = title.toString(); // column  
                       // String info = value.toString(); // value 
                        map.put(title, value);
                    }  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        } finally {  
            if (reader != null) {  
                // 关闭  
                try {  
                    reader.close();  
                } catch (Exception e) {  
                }  
            }  
        }  
        return map;
    }  
}
