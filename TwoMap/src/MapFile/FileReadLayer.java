/**    
 * 文件名：FileReadLayer.java    
 *    
 * 版本信息：    
 * 日期：2018年4月2日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package MapFile;

import java.io.File;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;

/**    
 *     
 * 项目名称：TwoMap    
 * 类名称：FileReadLayer    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2018年4月2日 下午8:29:27    
 * 修改人：jinyu    
 * 修改时间：2018年4月2日 下午8:29:27    
 * 修改备注：    
 * @version     
 *     
 */
public class FileReadLayer {
  public Layer readFile(String path) { 
        
        try{  
            File file = new File (path);  
            FileDataStore store = FileDataStoreFinder.getDataStore(file); 
            //设置字符编码  
            SimpleFeatureSource featureSource = store.getFeatureSource(); 
            Style style = SLD.createSimpleStyle(featureSource.getSchema());
            Layer layer = new FeatureLayer(featureSource, style);
            return layer;
        }
        catch(Exception ex)
        {
            
        }
        return null;
  }
  @SuppressWarnings("deprecation")
public Layer readSHP(String path) { 
      try{  
          File file = new File (path);  
          ShapefileDataStore shpDataStore = new ShapefileDataStore(file.toURL());  
          //设置字符编码  
          SimpleFeatureSource featureSource = shpDataStore.getFeatureSource(); 
          Style style = SLD.createSimpleStyle(featureSource.getSchema());
          Layer layer = new FeatureLayer(featureSource, style);
          return layer;
      }
      catch(Exception ex)
      {
          
      }
      return null;
}
}
