
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class test1 {


	public static String doGet(String httpUrl){
        
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        StringBuffer result = new StringBuffer();
        try {
            
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            connection.setReadTimeout(15000);
            //connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");

            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
           result.append(connection.getResponseCode()); 
            if (connection.getResponseCode() == 200) {
               
                is = connection.getInputStream();
                if (null != is) {
                    br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String temp = null;
                    while (null != (temp = br.readLine())) {
                        result.append(temp);
                    }
                }
            }
	else
	{
		System.out.println(httpUrl + "request was aborted error code " + connection.getResponseCode());

	}
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            connection.disconnect();
        }
        return result.toString();
    }
	public static String GetJuziNovel()
	{
		String result = "";
		//String req = "https://www.okex.com/api/spot/v3/instruments/LTC-USDT/trades?limit=2";
		String req = "https://v1.hitokoto.cn/?c=d";
		boolean flag = true;
		while(flag)
		{
			String temp = doGet(req);
			if(temp.equals(""))
			{
				System.out.println("retry"+ " "+temp);
				continue;
			}
			result = temp;
			result = "result:" +  temp;
			flag = false;
			
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(GetJuziNovel());
	}

}
