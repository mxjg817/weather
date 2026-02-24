# 一个简单的天气app

## 一：界面布局

### 1：城市选择
- 使用 Spinner 展示预定义城市列表，并自定义spinner布局，使其背景是透明的并且下拉时不会挡住上面的文字
- 同时用EditText 和 Button 实现手动输入城市名并搜索
### 2：天气展示
- ImageView 显示天气图标（如晴、阴、雨等）
- 三个 TextView 分别显示天气描述
- 从 WeatherBean 中取出 dayWeathers 列表的第一项（当天天气），获取温度、天气描述、风向风速等信息，设置到对应的 TextView
- 调用 getImg(String) 方法根据天气图标代码返回对应的图标资源 ID，设置给 ImageView。
### 3：未来七天天气预报区域
- 使用 RecyclerView-展示未来六天的天气，每行包含日期、天气图标和温度范围
- 从 WeatherBean 的 dayWeathers 列表中移除第一项（当天数据），剩余数据交给 Future_Weather 适配器。适配器在 onBindViewHolder 中为每个项设置日期、温度范围，并通过相同的 getImg 方法获取天气图标。
将适配器设置给 RecyclerView 并应用垂直布局管理器。
### 4：卡片区域
- 四个cardView分别展示空气、气压、能见度和湿度信息。
  ### 5：背景动态切换
  - 背景图片会根据当天天气类型动态更换为对应的背景图片，通过 findViewById(R.id.root_layout).setBackgroundResource(bgimg) 实时更换背景。
 
## 二、数据获取与网络请求
从两个不同的 API 接口获取天气数据
网络请求封装在 Netutil 类中，主要方法：

doGet(String urlStr)：使用 HttpURLConnection 发起 GET 请求，设置超时时间为5秒，读取返回的 JSON 字符串。

getweather_seven(String city) 和 getweather_hours(String city)：分别拼接对应 API 的 URL，调用 doGet 并返回结果。

## 三、数据处理
返回的 JSON 数据使用 Google Gson 库进行解析，定义了对应的 Java Bean 类

在 Handler 的 dispatchMessage 方法中，根据 msg.what 区分两种数据
所有网络请求均在子线程执行

https://github.com/user-attachments/assets/03b9abd7-e847-495d-be88-dfb010830fa5

