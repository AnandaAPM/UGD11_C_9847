package com.pbp.gd11_c_9847.ui.download;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

import java.util.List;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.DownloadViewHolder> {

    private List<DataDownload> data;
    private Context context;

    public DownloadAdapter(List<DataDownload> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public DownloadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.adapter_download,parent, false );
        return new DownloadViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull DownloadViewHolder holder, int position) {
        final DataDownload download = data.get ( position );
        final int[] id = {-1};

        PRDownloader.initialize ( context );
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder ()
                .setDatabaseEnabled ( true )
                .build ();
        PRDownloader.initialize ( context,config );
        String path = UtilityPR.getRootDirPath ( (Activity) context );

        holder.nama.setText ( download.getNama () );
        holder.size.setText ( Float.toString ( download.getBesar () ) + "MB");
        holder.btnCancel.setEnabled ( false );
        holder.btnCancel.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                PRDownloader.cancel ( id[0] );
            }
        } );

        holder.btnDownload.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (Status.RUNNING == PRDownloader.getStatus ( id[0] )){
                    PRDownloader.pause ( id[0] );
                    return;
                }
                holder.progressBar.setIndeterminate ( true );
                holder.progressBar.getIndeterminateDrawable ().setColorFilter ( Color.BLACK,
                        PorterDuff.Mode.SRC_IN );
                if (Status.PAUSED == PRDownloader.getStatus ( id[0] )){
                    PRDownloader.resume ( id[0] );
                    return;
                }

                id[0] = PRDownloader.download ( download.getUrl (),
                        path, download.getNama ()+download.getExt ())
                        .build ()
                        .setOnStartOrResumeListener ( new OnStartOrResumeListener () {
                            @Override
                            public void onStartOrResume() {
                                holder.progressBar.setIndeterminate ( false );
                                holder.btnDownload.setEnabled ( true );
                                holder.btnCancel.setEnabled ( true );
                                holder.btnDownload.setText ( "Berhenti" );
                                FancyToast.makeText ( context,"Download dimulai",FancyToast.LENGTH_SHORT,
                                        FancyToast.INFO,false ).show ();
                            }
                        } )
                        .setOnPauseListener ( new OnPauseListener () {
                            @Override
                            public void onPause() {
                                holder.btnDownload.setText ( "Lanjutkan" );
                                FancyToast.makeText ( context,"Download berhenti",FancyToast.LENGTH_SHORT,
                                        FancyToast.INFO,false ).show ();
                            }
                        } )
                        .setOnCancelListener ( new OnCancelListener () {
                            @Override
                            public void onCancel() {
                                holder.btnDownload.setEnabled(true);
                                holder.btnCancel.setEnabled(false);
                                holder.btnDownload.setText("Download");
                                id[0] = 0;
                                holder.progressBar.setProgress(0);
                                holder.size.setText(Float.toString ( download.getBesar () ));
                                holder.progressBar.setIndeterminate(false);
                                FancyToast.makeText(context, "File batal " +
                                        "didownload !", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                            }
                        } )
                        .setOnProgressListener ( new OnProgressListener () {
                            @Override
                            public void onProgress(Progress progress) {
                                String down = UtilityPR.getProgressDisplayLine ( progress.currentBytes,
                                        progress.totalBytes);
                                holder.nama.setText ( down );
                                long progres = progress.currentBytes*100/progress.totalBytes;
                                holder.progressBar.setProgress ( (int)  progres);
                                holder.progressBar.setIndeterminate ( false );
                            }
                        } )
                        .start ( new OnDownloadListener () {
                            @Override
                            public void onDownloadComplete() {
                                holder.btnDownload.setEnabled ( false );
                                holder.btnCancel.setEnabled ( false );
                                holder.btnDownload.setBackgroundColor(Color.GRAY);
                                holder.btnCancel.setText("Berhasil");
                                holder.btnDownload.setText("Downloaded");
                                FancyToast.makeText(context, "File berhasil" +
                                        " didownload !", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                            }

                            @Override
                            public void onError(Error error) {
                                holder.btnDownload.setEnabled(true);
                                holder.btnCancel.setEnabled(false);
                                holder.btnDownload.setText("Download");
                                id[0] = 0;
                                holder.progressBar.setIndeterminate(false);
                                holder.progressBar.setProgress(0);
                                holder.nama.setText("");
                                FancyToast.makeText(context, "Kesalahan" +
                                        " Jaringan !", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();

                            }
                        } );
            }
        } );

    }

    class DownloadViewHolder extends RecyclerView.ViewHolder{
        TextView nama,size;
        Button btnCancel,btnDownload;
        ProgressBar progressBar;

        public DownloadViewHolder(@NonNull View item){
            super(item );
            nama = item.findViewById ( R.id.tvNamaPertama );
            size = item.findViewById ( R.id.tvProgressPertama );
            btnCancel = item.findViewById ( R.id.btnCancelPertama );
            btnDownload = item.findViewById ( R.id.btnStartPertama );
            progressBar = item.findViewById ( R.id.pb1 );
        }


    }

    @Override
    public int getItemCount() {
        return data.size ();
    }
}
