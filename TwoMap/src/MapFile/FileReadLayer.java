/**    
 * �ļ�����FileReadLayer.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2018��4��2��    
 * Copyright ���� Corporation 2018     
 * ��Ȩ����    
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
 * ��Ŀ���ƣ�TwoMap    
 * �����ƣ�FileReadLayer    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2018��4��2�� ����8:29:27    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2018��4��2�� ����8:29:27    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class FileReadLayer {
  public Layer readFile(String path) { 
        
        try{  
            File file = new File (path);  
            FileDataStore store = FileDataStoreFinder.getDataStore(file); 
            //�����ַ�����  
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
          //�����ַ�����  
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
