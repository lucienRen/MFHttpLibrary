该工程为魔方HTTP网络请求封装的架构，该架构是基于OKHTTP架构封装而成，使用步骤架构如下：

第一初始化：

  1， 权限添加 
  
	uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"
    
    uses-permission android:name="android.permission.ACCESS_WIFI_STATE"
    
    uses-permission android:name="android.permission.INTERNET"
	
  2,添加工程最外部build.gradle配置信息：
    
	allprojects {
    repositories {
        jcenter()
        //第三方网络请求库
        maven {
            url "http://10.0.2.88:8081/artifactory/libs-release-local/"
        }
		}
	}
  3, 添加工程内部build.gradle配置信息：
	 
	 compile 'com.mfzp.http:mfhttplibrary:1.1.3'
	 compile 'com.squareup.okhttp3:okhttp:3.4.1'
     compile 'com.squareup.retrofit2:retrofit:2.1.0'
     compile 'com.google.code.gson:gson:2.7'

  4，Application的初始化配置
       
		
		MFHttpConfiguration configuration = MFHttpConfiguration.Builder(this).setBaseUrl("http://101.201.57.61:3261").addCommParams("http://101.201.57.61:3261", mfmap);
        MFHttpManager.instance(this).setConfiguration(configuration);
		
		       addCommParams(map)参数map 为公共参数
						例如： HashMap map = new HashMap<>();
								map.put("device", "GT-I9500");
								map.put("os", "Android");
								map.put("osVersion", "5.0.1");
								map.put("carrier", "CHINA MOBILE");
								map.put("resolution", "1080x1920");
								map.put("locale", "zh_CN");
								map.put("appVersion", "2.0.2");
								map.put("download_chanle", "yingyongbao");
								map.put("imei", "358851059063176");
					如果不需要可以设置null
			   
  5，在baseActivity中进行初始化对象便于其子类进行调用
  
	    MFHttpManager   mfHttpManager = MFHttpManager.instance(this);
		
第二请求调用：
 
	1,在子Activity中调用get请求如下：
   
      mfHttpManager.executeByBody("/v2/app-b/recommend-resumes/search/my?start=0&count=15", null, new IMFFactory.MFHttpCallBackListener() {
                    @Override
                    public void callback(Object result, int code, String message) {
                        //TODO
                    }
                });
				
	           备注：1,executeByBody(String url, JSONObject obj, Class clazz, IMFFactory.MFHttpCallBackListener listener)
			        
					
					第一个参数url为对应的请求相对路径（除了域名以外的URL）
					     例如：/v2/app-b/recommend-resumes/search/my?start=0&count=15
				 
					第二个参数obj 为请求的body的值例如：{"name":"zhangsan"} 
					
			   	    第三个参数clazz 为请求之后需要获取的对象，如果不需要或者需要直接的JOSNOBJECT可以传NULL
						  例如： mfHttpManager.executeByBody("/v2/app-b/recommend-resumes/search/my?start=0&count=15", null,new IMFFactory.MFHttpCallBackListener() {
									@Override
									public void callback(Object result, int code, String message) {
										//TODO
										}
									});
									
					 第四个参数为回调；在不需要的情况下可以为NULL；
					       例如： mfHttpManager.executeByBody("/v2/app-b/recommend-resumes/search/my?start=0&count=15",  null,null);
					 
					2,对于回调callback(Object result, int code, String message)
					
					第一个参数为返回的结果，如果请求参数的clazz 传NULL 的情况下，其result为JSONOBJECT对象
					     如果clazz参数传实体类对象其result返回的也是该对象，举例如下：
						  
						  mfHttpManager.executeByBody("/v2/app-b/recommend-resumes/search/my?start=0&count=15",  HRPersonBean.class, new IMFFactory.MFHttpCallBackListener()<HRPersonBean>() {
							@Override
							public void callback(HRPersonBean result, int code, String message) {
							//TODO
							}
						});
	
第三方法介绍：

			executeByBody(HttpType httpType, String url, JSONObject obj, Class clazz, IMFFactory.MFHttpCallBackListener listener)
    	  
			    备注：httptype:对应的请求方式 POST,PUT,DELETE;
					url 对应的相对url(除了域名以外的URL)   例如：/user/loginByPassword.json
					obj 对应body的值
					clazz 为请求之后需要获取的对象，如果不需要或者需要直接的JOSNOBJECT可以传NULL
					listener 对应的回调 可以为NULL
					
					
			executeByBody(String url, JSONObject obj, Class clazz, IMFFactory.MFHttpCallBackListener listener)
			
				备注:POST 请求
					url 对应的相对url(除了域名以外的URL)   例如：/user/loginByPassword.json
					obj 对应body的值
					clazz 为请求之后需要获取的对象，如果不需要或者需要直接的JOSNOBJECT可以传NULL
					listener 对应的回调 可以为NULL
					
			execute(String url, Class clazz, IMFFactory.MFHttpCallBackListener listener)
					备注:GET 请求
					url 对应的相对url(除了域名以外的URL)	例如：/user/loginByPassword.json
					clazz 为请求之后需要获取的对象，如果不需要或者需要直接的JOSNOBJECT可以传NULL
					listener 对应的回调 可以为NULL
					
			execute(HttpType httpType, String url, Class clazz, IMFFactory.MFHttpCallBackListener listener)
					
				备注：httptype:对应的请求方式 GET,PUT,DELETE;
					url 对应的相对url(除了域名以外的URL) /user/loginByPassword.json
					clazz 为请求之后需要获取的对象，如果不需要或者需要直接的JOSNOBJECT可以传NULL
					listener 对应的回调 可以为NULL
			
		    executeByAbsoluteUrl(HttpType httpType, String url, String params, Class clazz, IMFFactory.MFHttpCallBackListener listener) 
			
				备注：httptype:对应的请求方式 POST,GET;
					url 对应的绝对路径url   例如： http://101.201.57.61:3261/user/loginByPassword.json
					params 对应的params的值 例如：{"name":"zhangsan"} 
					clazz 为请求之后需要获取的对象，如果不需要或者需要直接的JOSNOBJECT可以传NULL
					listener 对应的回调 可以为NULL
		
			
			executeByAbsoluteUrl(HttpType httpType, String url, Class clazz, IMFFactory.MFHttpCallBackListener listener)				
					
				备注：httptype:对应的请求方式 POST,GET;
					url 对应的绝对路径url   例如： http://101.201.57.61:3261/user/loginByPassword.json
					params 对应的params的值 例如：{"name":"zhangsan"} 
					clazz 为请求之后需要获取的对象，如果不需要或者需要直接的JOSNOBJECT可以传NULL
					listener 对应的回调 可以为NULL
		

			executeByParams(HttpType httpType, String url, String params, Class clazz, IMFFactory.MFHttpCallBackListener listener)
			
		        备注：httptype:对应的请求方式 POST,GET;
					url 对应的相对url(除了域名以外的URL)  例如： /user/loginByPassword.json
					params 对应的params的值 例如：{"name":"zhangsan"} 
					clazz 为请求之后需要获取的对象，如果不需要或者需要直接的JOSNOBJECT可以传NULL
					listener 对应的回调 可以为NULL
           
					
					
					
		
概况，该项目目前功能比较简单，适合Android所有所有的网络请求，不过有一部分功能还尚未添加，在后续版本会陆陆续续的添加上去，为了是该框架能过慢慢的壮大，如果有个人宝贵的意见希望能够多多提出
