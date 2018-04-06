package MapTool;
import java.io.File;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.swing.JMapFrame;
import MapFile.FileReadLayer;

/**    
 * �ļ�����MapTools.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2018��4��2��    
 * Copyright ���� Corporation 2018     
 * ��Ȩ����    
 *    
 */

/**    
 *     
 * ��Ŀ���ƣ�TwoMap    
 * �����ƣ�MapTools    
 * ��������    
 * �����ˣ�jinyu    
 * ����ʱ�䣺2018��4��2�� ����1:18:38    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2018��4��2�� ����1:18:38    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class MapTools {

    /**
     * @param args
     */
    public static void main(String[] args) {
      
          try
          {
       
        MapContent map = new MapContent();
        map.setTitle("Using cached features");
        //

        File baseDir = new File("E:\\gis\\50m_cultural");       // ����һ��File����  
        if (!baseDir.exists() || !baseDir.isDirectory()) {  // �ж�Ŀ¼�Ƿ����  
            System.out.println("�ļ�����ʧ�ܣ�"  + "����һ��Ŀ¼��");  
        }  
        File[] files = baseDir.listFiles();
        FileReadLayer rdlayer=new FileReadLayer();
        for(File f : files )
        {
            if(f.getName().endsWith(".shp"))
            {
                Layer layer = rdlayer.readFile(f.getAbsolutePath());
                if(layer!=null)
                map.addLayer(layer);
            }
        }
       

        // Now display the map
        JMapFrame.showMap(map);
          }
          catch(Exception ex)
          {
              
          }
          

    }

}