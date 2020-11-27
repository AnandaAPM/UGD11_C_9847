package com.pbp.gd11_c_9847.ui.download;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Progress;
import com.downloader.Status;
import com.pbp.gd11_c_9847.R;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

public class DownloadFragment extends Fragment {
    private List<DataDownload> downloadList;
    private DownloadViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(DownloadViewModel.class);
        View v = inflater.inflate ( R.layout.fragment_download,container,false );

        downloadList = new ArrayList<> ();
        data();

        RecyclerView rv = v.findViewById ( R.id.rvdwn );
        rv.setLayoutManager ( new LinearLayoutManager ( getContext () ) );
        rv.setAdapter ( new DownloadAdapter ( downloadList,getContext () ) );

        return v;

    }

    private void data() {
        downloadList.add(new DataDownload ( "PDF",".pdf", "https://www101.zippyshare.com/d/mVDYTh1r/314435/UGD11_C_9847.pdf",0.03f));
        downloadList.add(new DataDownload ( "Music",".mp3", "https://www100.zippyshare.com/d/ftbGAx71/2628075/Adera - Aku Harus Pergi.mp3",9.7f));
        downloadList.add(new DataDownload ( "Gambar",".jpg", "https://www.mordeo.org/files/uploads/2020/11/Horizon-Apex-Legends-4K-Ultra-HD-Mobile-Wallpaper-950x1689.jpg",0.3f));

    }
}