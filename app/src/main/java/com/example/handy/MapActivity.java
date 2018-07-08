package com.example.handy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.map.MapStatus;
import com.example.handy.R;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by t520win764zy on 2017/12/11.
 */

public class MapActivity extends AppCompatActivity {
    private String build;
    private double lat;
    private double lon;

    public LocationClient mLocationClient = null;
    public MyLocationListener myLocationListener = null;

    public Button button_request;
    public TextView text_location;
    private MapView mapView;
    private BaiduMap baiduMap;//BaiduMap是地图的总控制器
    private boolean isFirstLocate = true;//该变量为了防止对此定位，因为定位我的位置一次就够

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());//显示地图画面的初始化  注意：该操作一定在setContentView方法前调用，不然会出错

        setContentView(R.layout.empty_result_map_layout);

        Bundle bundle=getIntent().getExtras();//获取bundle中的build值，下面会根据这个值对经纬度赋不同的值
        build = bundle.getString("build");

        button_request = (Button) findViewById(R.id.request_map);//各控件的定义
        text_location = (TextView) findViewById(R.id.position_text_view);
        mapView = (MapView) findViewById(R.id.bmapView);

        mLocationClient = new LocationClient(MapActivity.this);//初始化百度地图定位功能,之后可直接使用
        LocationClientOption option = new LocationClientOption();//利用option对象设置一些参数
        option.setIsNeedAddress(true);//设置是否需要地址信息
        option.setCoorType("bd09ll");//setCoorType设置坐标类型  百度坐标(bd0911 注意是字母l而不是数字1)互转火星坐标(gcj02)和国测局坐标(GPS)
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);//设置定位模式  Battery_Saving为低功耗定位模式
        option.setScanSpan(5 * 60000);//setCoorType 设置发起定位请求的间隔 自己设置时间 毫秒单位
        mLocationClient.setLocOption(option);//将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);

        myLocationListener = new MyLocationListener();//百度地图定位功能
        mLocationClient.registerLocationListener(myLocationListener);//注册定位监听器
        mLocationClient.start();//开始定位

        button_request.setOnClickListener(new View.OnClickListener() {//点击按钮 开始定位
            @Override
            public void onClick(View view) {
                if(build.equals("一教")){
                    lat=39.883658;
                    lon=116.48593;
                }else if(build.equals("三教")){
                    lat=39.881858;
                    lon=116.487414;
                }else if(build.equals("信息楼")){
                    lat=39.8808;
                    lon=116.485511;
                }else if(build.equals("材料楼")){
                    lat=39.883608;
                    lon=116.485925;
                }else if(build.equals("知行楼")){
                    lat=39.879427;
                    lon=116.493136;
                }
                navigateTo(lat,lon);
            }
        });
    }
    private void navigateTo(double lat,double lon) {//根据location移动到我的位置的函数  以及设置地图样式，eg缩放级别等
        if (isFirstLocate) {
            LatLng ll = new LatLng(lat, lon);//设置对应的经纬度数值
            ll = pianyi(lat,lon);//计算解决部分偏移
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);//将返回的对象update传进animateMapStatus即可完成update中的操作
            update = MapStatusUpdateFactory.zoomTo(12f);//显示缩放级别 数值越大，显示的越精细
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;//该变量为了防止对此定位，因为定位我的位置一次就够
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(lat);
        locationBuilder.longitude(lon);
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);//利用百度地图封装好的函数setMyLocationData将包含经纬度信息的数据locationData绑定到我们画面中的地图上
    }
    private LatLng pianyi(double lon, double lat){//设置位置偏移问题
        double x = lon; double y = lat;
        double z = Math.sqrt(x*x+y*y) + 0.00002 *Math.sin(y*Math.PI) ;
        double temp =Math.atan2(y, x)  + 0.000003 * Math.cos(x*Math.PI);
        double bdLon = z * Math.cos(temp) + 0.0065;
        double bdLat = z * Math.sin(temp) + 0.006;
        LatLng newcenpt = new LatLng(bdLat, bdLon);
        return newcenpt;
    }

    public class MyLocationListener implements BDLocationListener {//回调 监听器，类似于按钮点击事件的监听器 等 作用，这里用来监听定位
        @Override
        public void onReceiveLocation(BDLocation location) {//回调函数 用于接收地址信息
            StringBuffer stringBuffer = new StringBuffer(String.valueOf(location));
            text_location.setText("\n①您当前的位置是:"+location.getCountry()+location.getProvince()+location.getCity()+location.getDistrict()+location.getStreet()+
                                    "\n②点击下方按钮可以获得 * "+build+" * 的定位:");
        }
    }

    @Override
    protected void onResume(){//在onResume之后调用
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

}
