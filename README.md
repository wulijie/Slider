# Slider
```
    使用Slider,轻松实现页面侧滑删除的功能
```
##### [![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](https://www.apache.org/licenses/LICENSE-2.0)

---

#### 最新版本
  Slider最新版本![Download](https://api.bintray.com/packages/wulijie/maven/Slider/images/download.svg)
  
  
#### 效果展示


#### 如何使用
1. Gradle集成方式

  ```
	compile 'com.warpdrive.slider:slider:最新版本'

  ```
2. Maven集成方式


  ```
<dependency>
  <groupId>com.warpdrive.slider</groupId>
  <artifactId>slider</artifactId>
  <version>1.0.2</version>
  <type>pom</type>
</dependency>

  ```
  
3. 初始化

	```
	//在BaseActivity onCreate()方法内初始化
public class BaseActivity extends AppCompatActivity {
	 
	 protected SlidePage	mSlidePage；//抽出成员变量
	
	  @Override
      protected void onCreate(Bundle savedInstanceState){
		
	    mSlidePage = Slider.bind(this)
                .setSlideEnable(isSlide())
                .setSlideEdge(100)//可滑动的范围。实际像素。200表示为左边200px的屏幕(默认是100)
                //.setSlideEdgePercent(0.2f)//可滑动的范围。百分比。0.2表示为左边20%的屏幕(与setSlideEdge 功能一致使用一个就可以)
                .setSlideRelatedEnable(true)//是否支持当前页面下的页面跟随联动（默认是true）
                .setSlideRelatedOffset(300)//与上面的参数配合使用，连动差距（默认300）
                //.setScrimColor()//设置滑动时蒙层的颜色（默认是透明 仿微信）
                .setSlideSensitivity(0.5f)//对横向滑动手势的敏感程度。0为迟钝 1为敏感(默认0.5f)
	}
	
	
	//在Activity的onPostCreate 生命周期内添加
	
	@Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //这个别忘了加上！
        mSlidePage.onPostCreate();
    }
    
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除侧滑删除
        Slider.unBind(mSlidePage);
    }
		
	```
4. 管理Activity
	
	```
	
		Slider.finishAll();//清空所有页面
        Slider.finishAll(Class cls);//除某一个页面外 其余都清空
        Slider.getCurActivity();//获取栈顶Activity
        Slider.getPreActivity();//获取栈顶下的Activity
	
	```
