import java.net.URI;
import java.net.URISyntaxException;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.avlist.AVListImpl;
import gov.nasa.worldwind.ogc.wms.WMSCapabilities;
import gov.nasa.worldwind.wms.WMSTiledImageLayer;
import gov.nasa.worldwindx.examples.ApplicationTemplate;

/**    
 * 文件名：WMSLayerTest.java    
 *    
 * 版本信息：    
 * 日期：2018年4月1日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */

/**    
 *     
 * 项目名称：NasWorldWind    
 * 类名称：WMSLayerTest    
 * 类描述：    加载geoserver服务资源
 * 创建人：jinyu    
 * 创建时间：2018年4月1日 下午12:59:58    
 * 修改人：jinyu    
 * 修改时间：2018年4月1日 下午12:59:58    
 * 修改备注：    
 * @version     
 *     
 */
public class WMSLayerTest  extends ApplicationTemplate {  
        public static class AppFrame extends ApplicationTemplate.AppFrame{  
            private static final long serialVersionUID = 1L;  
       
            public AppFrame(){  
                try {  
                    //请求地图的URL  
                    String uri = "http://127.0.0.1:8080/geoserver/PostGIS/wms";  
                    WMSCapabilities caps;  
                    URI serverURI = new URI(uri);  
       
                    //获得WMSCapabilities对象  
                    caps = WMSCapabilities.retrieve(serverURI);  
                    //解析WMSCapabilities数据  
                    caps.parse();  
       
                    AVList params = new AVListImpl();  
       
                    //图层的名称  
                    params.setValue(AVKey.LAYER_NAMES, "planet_osm_line");  
                    //地图服务的协议，这里是OGC:WMS  
                    params.setValue(AVKey.SERVICE_NAME, "OGC:WMS");  
                    //获得地图的uri，也就是上面定义的uri  
                    params.setValue(AVKey.GET_MAP_URL, uri);  
                    //在本地缓存文件的名称  
                    params.setValue(AVKey.DATA_CACHE_NAME, "planet_osm_line");  
                    params.setValue(AVKey.TILE_URL_BUILDER, new WMSTiledImageLayer.URLBuilder(params));  
       
                    WMSTiledImageLayer imageLayer = new WMSTiledImageLayer(caps,params);  
                    //图层名称  
                    imageLayer.setName("planet_osm_line");  
                    imageLayer.setEnabled(true);  
                    //图层的透明度  
                    imageLayer.setOpacity(1);  
                    //图层的最大显示高度  
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
            ApplicationTemplate.start("WMS图层", WMSLayerTest.AppFrame.class);  
        }  
}
