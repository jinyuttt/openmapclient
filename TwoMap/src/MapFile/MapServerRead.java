/**    
 * 文件名：MapServerRead.java    
 *    
 * 版本信息：    
 * 日期：2018年4月3日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package MapFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.geotools.data.ows.HTTPClient;
import org.geotools.data.ows.Layer;
import org.geotools.data.ows.WMSCapabilities;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.wms.WMS1_3_0;
import org.geotools.data.wms.WebMapServer;
import org.geotools.data.wms.request.GetMapRequest;
import org.geotools.data.wms.response.GetMapResponse;
import org.geotools.ows.ServiceException;
import org.geotools.swing.wms.WMSChooser;
import org.geotools.swing.wms.WMSLayerChooser;

/**    
 *     
 * 项目名称：TwoMap    
 * 类名称：MapServerRead    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2018年4月3日 下午1:21:04    
 * 修改人：jinyu    
 * 修改时间：2018年4月3日 下午1:21:04    
 * 修改备注：    
 * @version     
 *     
 */
public class MapServerRead {
    public static String srvAddr="";
    public static URL getCapabiltiesURL(URL mapURL) {
        URL url = mapURL;
        try {
            WebMapServer wms = new WebMapServer(mapURL);
            HTTPClient httpClient = wms.getHTTPClient();
            URL get = wms.
                    getCapabilities().
                    getRequest().
                    getGetCapabilities().
                    getGet();
            if (get != null) {
                url = new URL(get.toString() + "request=GetCapabilities");
            }
            httpClient.getConnectTimeout();
        } catch (IOException | ServiceException e) {
           
        }
        return url;
    }
    
    /**
     * 获取
     * @return
     */
 public WebMapServer getMapServer()
 {
     URL url=null;
     URL capabilitiesURL = WMSChooser.showChooseWMS();
     if(capabilitiesURL==null)
     {
         try {
             url=new URL(srvAddr);
         } catch (MalformedURLException e) {
           
             e.printStackTrace();
         }
     }
     else
     {
         url=capabilitiesURL;
     }
     WebMapServer wms = null;
     try {
         wms = new WebMapServer(url);
     } catch (ServiceException e) {
      
         e.printStackTrace();
     } catch (IOException e) {
        
         e.printStackTrace();
     }
    return wms;
 }
 
 /**
  * 获取所有层
  * @return
  */
public   List<Layer> readMap()
{
    WebMapServer wms = getMapServer();
    List<Layer> wmsLayers = WMSLayerChooser.showSelectLayer( wms );
    return wmsLayers;
}


   
/**   
 * @Title: loadLayers   
 * @Description: TODO(这里用一句话描述这个方法的作用)   
 * @return      
 * List<Layer>      
 * @throws   
 */
 
public List<Layer>  loadLayers()
{
    WebMapServer wms = getMapServer();
    List<Layer> wmsLayers =wms.getCapabilities().getLayerList();
    return wmsLayers;
}


   
/**   
 * @Title: getWMSLayer   
 * @Description: 获取特定层   
 * @param layerName
 * @return      
 * Layer      
 * @throws   
 */

public Layer getWMSLayer(String layerName)
{
    WebMapServer wms = getMapServer();
    for( org.geotools.data.ows.Layer layer : wms.getCapabilities().getLayerList() ) {
        if (layerName.equals(layer.getName())) {
            return layer;
        }
    }
    return null;
    
}

}
