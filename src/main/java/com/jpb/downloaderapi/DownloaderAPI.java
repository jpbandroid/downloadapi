package com.jpb.downloaderapi;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

public class DownloaderAPI {
    public static void download(Context context, String uri, String filetitle, String filename) {
        DownloadManager downloadmanager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri urifunction = Uri.parse(uri);

        DownloadManager.Request request = new DownloadManager.Request(urifunction);
        request.setTitle(filetitle);
        request.setDescription("Downloading");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
        downloadmanager.enqueue(request);
    }
}
