package com.girish.libraryv1.ServerCallByHttp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.girish.libraryv1.Constants.Constants;
import com.girish.libraryv1.Implements.CallBackServerRes;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Girish on 16/10/2017.
 */

class HTTPURLConnection extends AsyncTask<HashMap<String,String>,Integer,String> {

    private ProgressDialog dialog;
    private String response;
    @SuppressLint("StaticFieldLeak")
    private Activity a;
    @SuppressLint("StaticFieldLeak")
    private Fragment f;
    private int key;
    private String strKey;
    private File file;
    private int progress = 0;
    private long totalSize;
    private boolean withHeader,isDialog;
    private boolean isGet;
    private String fileKey;
    private Integer sentType;
    private HashMap<String,String> passParams;
//    public HTTPURLConnection(Activity a, String strKey, boolean withHeader)
//    {
//        this.a = a;
//        this.strKey = strKey;
//        this.withHeader = withHeader;
//    }
//
//    public HTTPURLConnection(Fragment f, String strKey, boolean withHeader) {
//        this.f=f;
//        this.strKey=strKey;
//        this.withHeader=withHeader;
//    }

    HTTPURLConnection(Fragment f, String strKey, int key, boolean withHeader) {
        this.f=f;
        this.strKey=strKey;
        this.key = key;
        this.withHeader=withHeader;
    }

    HTTPURLConnection(Fragment f, String strKey, int key, Integer sentType) {
        this.f=f;
        this.strKey=strKey;
        this.key = key;
        this.sentType = sentType;
    }

    HTTPURLConnection(Fragment f, String strKey, int key, boolean withHeader, Integer sentType) {
        this.f=f;
        this.strKey=strKey;
        this.key = key;
        this.withHeader=withHeader;
        this.sentType = sentType;
    }

    public HTTPURLConnection(Integer sentType, Fragment f, String strKey) {
        this.f=f;
        this.strKey=strKey;
        this.sentType = sentType;
    }

    private boolean isProcessingInVisible() {
        return processingInVisible;
    }

//    public void setProcessingInVisible(boolean processingInVisible) {
//        this.processingInVisible = processingInVisible;
//    }

    private boolean processingInVisible;
    HTTPURLConnection(Fragment f, String strKey){
        this.f = f;
        this.strKey=strKey;
        withHeader = true;
    }

//    HTTPURLConnection(Fragment f, String strKey, File file){
//        this.f=f;
//        this.strKey=strKey;
//        this.file=file;
//    }

    HTTPURLConnection(Fragment f, String strKey, int key){
        this.f=f;
        this.strKey=strKey;
        this.key = key;
    }

//    HTTPURLConnection(Activity a, String strKey){
//        this.a=a;
//        this.strKey=strKey;
//    }
//
//    HTTPURLConnection(Fragment f, String strKey, File file, int key) {
//        this.f=f;
//        this.strKey=strKey;
//        this.file=file;
//        this.key = key;
//    }
//
//
//    HTTPURLConnection(Fragment f, String strKey ,boolean withHeader,boolean isGet){
//        this.f=f;
//        this.strKey=strKey;
//        this.withHeader=withHeader;
//        this.isGet=isGet;
//    }
//
//    HTTPURLConnection(Fragment f, String strKey, File file,boolean withHeader,boolean isGet){
//        this.f=f;
//        this.strKey=strKey;
//        this.file=file;
//        this.withHeader=withHeader;
//        this.isGet=isGet;
//    }
//
//    HTTPURLConnection(Fragment f, String strKey,int key,boolean withHeader,boolean isGet){
//        this.f=f;
//        this.strKey=strKey;
//        this.key = key;
//        this.withHeader=withHeader;
//        this.isGet=isGet;
//    }
//
//    HTTPURLConnection(Activity a, String strKey,boolean withHeader,boolean isGet){
//        this.a=a;
//        this.strKey=strKey;
//        this.withHeader=withHeader;
//        this.isGet=isGet;
//    }
//
//    HTTPURLConnection(Fragment f, String strKey, File file,String fileKey, int key,boolean withHeader,boolean isGet) {
//        this.f=f;
//        this.strKey=strKey;
//        this.file=file;
//        this.key = key;
//        this.withHeader=withHeader;
//        this.isGet=isGet;
//        this.fileKey = fileKey;
//    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        try {
            if (isDialog) {
                if (f != null) dialog = new ProgressDialog(f.getActivity());
                else dialog = new ProgressDialog(a);
                dialog.setMessage("Loading...");
                dialog.setIndeterminate(false);
                dialog.setCancelable(false);
                if (file != null) {
                    dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    dialog.setMax(100);
                    dialog.setProgress(progress);
                }
                if (!isProcessingInVisible()) dialog.show();
            }
        } catch (Exception e){
            e.getLocalizedMessage();
        }
    }

    @SafeVarargs
    @Override
    protected final String doInBackground(@Nullable HashMap<String, String>... params)
    {
        HashMap<String,String> h = new HashMap<>();
        String responseBody="Error Not Found";
        if (file!=null) totalSize = file.length();
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            String url = Constants.WEB_HOST_URL+Constants.URL_MOBILE_SERVICE+strKey;
            if (isGet)
            {
                HttpGet httpGet = new HttpGet(url);
                MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE){
                    @Override
                    public void writeTo(OutputStream outstream) throws IOException {
                        super.writeTo(new CoutingOutputStream(outstream));
                    }
                };
                if (params!=null) setMultipartEntity(params[0],multipartEntity);
                else setMultipartEntity(h,multipartEntity);
                if (params!=null)passParams = params[0];
                else passParams = h;
                HttpResponse response = httpclient.execute(httpGet);
                responseBody = EntityUtils.toString(response.getEntity());
            }
            else
                {
                HttpPost httppost = new HttpPost(url);
                if (this.withHeader)
                {
                    httppost.addHeader("Accept-Encoding","application/json");
                    httppost.addHeader("Content-Type","application/json");
                    httppost.addHeader("Accept-Language","en/US");
                }
                MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE){
                    @Override
                    public void writeTo(OutputStream outstream) throws IOException {
                        super.writeTo(new CoutingOutputStream(outstream));
                    }
                };
                if (params!=null){
                    if (Objects.equals(Constants.RAW_TYPE, sentType)){
                        String postParameters2 =params[0].get("RAW_DATA");
                        if(!postParameters2.isEmpty())
                            httppost.setEntity(new StringEntity(postParameters2,"UTF-8"));
                    }
                    else
                      setMultipartEntity(params[0],multipartEntity);
                }
                else setMultipartEntity(h,multipartEntity);
                HttpResponse response = httpclient.execute(httppost);
                responseBody = EntityUtils.toString(response.getEntity());
            }



        } catch (Exception e) {
            e.printStackTrace();
            responseBody = e.getMessage();
            if (responseBody==null){
                responseBody="Error Not Found";
            }
        }

        return responseBody;
    }

    private void setMultipartEntity(HashMap<String, String> params, MultipartEntity multipartEntity) throws UnsupportedEncodingException {
        if (params!=null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                multipartEntity.addPart(entry.getKey(),new StringBody(entry.getValue()));
            }
        }
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        if (params!=null) {
            boolean first = true;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
        }

        return result.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (isDialog)if (dialog!=null)if (dialog.isShowing())dialog.dismiss();
        CallBackServerRes callBackServerRes;
        if (f!=null){
            callBackServerRes= (CallBackServerRes) f;
        }else{
            callBackServerRes= (CallBackServerRes)a;
        }
        callBackServerRes.CallBackServerRes(s,key);
        callBackServerRes.CallBackServerRes(s,strKey);
        callBackServerRes.CallBackServerRes(s,strKey,passParams);

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (isDialog)dialog.setProgress(values[0]);

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        if (isDialog)dialog.setProgress(progress);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
        if (isDialog)
            if (dialog.isShowing()) {
                dialog.setProgress(progress);
                if (s != null) {
                    dialog.setMessage(s);
                    dialog.dismiss();
                }
            }

    }

    class CoutingOutputStream extends FilterOutputStream {
        long totalSent;
        CoutingOutputStream(final OutputStream out) {
            super(out);
            totalSent = 0;

        }

        @Override
        public void write(int b) throws IOException {
            out.write(b);

        }

        @Override
        public void write(byte[] b) throws IOException {
            out.write(b);

        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            totalSent += len;
            int progress = (int) ((totalSent / (float) totalSize) * 100);
            publishProgress(progress);
            out.write(b, off, len);

        }

    }
}
