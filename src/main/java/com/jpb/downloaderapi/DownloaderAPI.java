package com.jpb.downloaderapi;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import java.lang.Exception;

public class DownloaderAPI {
    public static void download(Context context, String uri, String filetitle, String filename, Boolean downloadingTextIsNew, Integer SaveDirectory, Boolean showDownloadProgress) {
        DownloadManager downloadmanager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri urifunction = Uri.parse(uri);

        DownloadManager.Request request = new DownloadManager.Request(urifunction);
        request.setTitle(filetitle);
        if (!downloadingTextIsNew) {
            request.setDescription("Downloading");
        } else {
            if (!showDownloadProgress) {
                request.setDescription("Downloading...");
            } else {
                request.setDescription("Downloading... " + DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR + " / " + DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
            }
        }
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        if (SaveDirectory == 1) {
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
        } else if (SaveDirectory == 2) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOCUMENTS, filename);
            } else {
                Toast.makeText(context.getApplicationContext(),
                        "Your device is running Android version below 4.4 (KitKat), so your file will be saved to Downloads folder instead of Documents folder.",
                        Toast.LENGTH_LONG).show();
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
            }
        } else if (SaveDirectory == 3) {
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DCIM, filename);
        } else if (SaveDirectory == 4) {
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_NOTIFICATIONS, filename);
        } else if  (SaveDirectory == 5)  {
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC, filename);
        } else {
            SaveDirectory = 0;
        }
        downloadmanager.enqueue(request);
    }
}
