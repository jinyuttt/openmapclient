import java.net.URI;
import java.net.URISyntaxException;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.avlist.AVListImpl;
import gov.nasa.worldwind.ogc.wms.WMSCapabilities;
import gov.nasa.worldwind.wms.WMSTiledImageLayer;
import gov.nasa.worldwindx.examples.ApplicationTemplate;

/**    
 * �ļ�����WMSLayerTest.java    
 *    
 * �汾��Ϣ��    
 * ���ڣ�2018��4��1��    
 * Copyright ���� Corporation 2018     
 * ��Ȩ����    
 *    
 */

/**    
 *     
 * ��Ŀ���ƣ�NasWorldWind    
 * �����ƣ�WMSLayerTest    
 * ��������    ����geoserver������Դ
 * �����ˣ�jinyu    
 * ����ʱ�䣺2018��4��1�� ����12:59:58    
 * �޸��ˣ�jinyu    
 * �޸�ʱ�䣺2018��4��1�� ����12:59:58    
 * �޸ı�ע��    
 * @version     
 *     
 */
public class WMSLayerTest  extends ApplicationTemplate {  
        public static class AppFrame extends ApplicationTemplate.AppFrame{  
            private static final long serialVersionUID = 1L;  
       
            public AppFrame(){  
                try {  
                    //�����ͼ��URL  
                    String uri = "http://127.0.0.1:8080/geoserver/PostGIS/wms";  
                    WMSCapabilities caps;  
                    URI serverURI = new URI(uri);  
       
                    //���WMSCapabilities����  
                    caps = WMSCapabilities.retrieve(serverURI);  
                    //����WMSCapabilities����  
                    caps.parse();  
       
                    AVList params = new AVListImpl();  
       
                    //ͼ�������  
                    params.setValue(AVKey.LAYER_NAMES, "planet_osm_line");  
                    //��ͼ�����Э�飬������OGC:WMS  
                    params.setValue(AVKey.SERVICE_NAME, "OGC:WMS");  
                    //��õ�ͼ��uri��Ҳ�������涨���uri  
                    params.setValue(AVKey.GET_MAP_URL, uri);  
                    //�ڱ��ػ����ļ�������  
                    params.setValue(AVKey.DATA_CACHE_NAME, "planet_osm_line");  
                    params.setValue(AVKey.TILE_URL_BUILDER, new WMSTiledImageLayer.URLBuilder(params));  
       
                    WMSTiledImageLayer imageLayer = new WMSTiledImageLayer(caps,params);  
                    //ͼ������  
                    imageLayer.setName("planet_osm_line");  
                    imageLayer.setEnabled(true);  
                    //ͼ���͸����  
                    imageLayer.setOpacity(1);  
                    //ͼ��������ʾ�߶�  
                    imageLayer.setMaxActiveAltitude(33500000);  
                    getWwd().getModel().getLayers().add(imageLayer);  
                    getLayerPanel().update(getWwd());  
       
                } catch (URISyntaxException e) {  
                    e.printStackTrace();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
       
        }  
    public static void main(String[] args) {  
            ApplicationTemplate.start("WMSͼ��", WMSLayerTest.AppFrame.class);  
        }  
}