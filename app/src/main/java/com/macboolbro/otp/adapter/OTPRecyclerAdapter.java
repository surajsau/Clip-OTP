package com.macboolbro.otp.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.macboolbro.otp.IConstants;
import com.macboolbro.otp.R;
import com.macboolbro.otp.activity.SmsListingActivity;
import com.macboolbro.otp.db.OTPModel;
import com.macboolbro.otp.listener.OnItemDeleteListener;

import java.util.ArrayList;

/**
 * Created by MacboolBro on 11/02/16.
 */
public class OTPRecyclerAdapter extends RecyclerView.Adapter<OTPRecyclerAdapter.OTPViewHolder> {

    private ArrayList<OTPModel> mModels;
    private Context mContext;
    private OnItemDeleteListener dismissListener;

    public OTPRecyclerAdapter(Context context, ArrayList<OTPModel> models) {
        mModels = new ArrayList<>();
        mModels.addAll(models);
        mContext = context;

        dismissListener = (SmsListingActivity)context;
    }

    @Override
    public OTPViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowLayout = LayoutInflater.from(mContext).inflate(R.layout.row_otp_sms, parent, false);
        return new OTPViewHolder(rowLayout);
    }

    @Override
    public void onBindViewHolder(OTPViewHolder holder, int position) {
        OTPModel model = mModels.get(position);

        holder.tvSender.setText(model.getSender());
        holder.tvMessage.setText(model.getMessage());
        holder.tvOtp.setText(model.getOtp());

        holder.setModel(model);
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    public class OTPViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvSender;
        private TextView tvOtp;
        private TextView tvMessage;
        private ImageButton btnDismiss;
        private Button btnCopyOtp;

        private OTPModel model;

        public OTPViewHolder(View itemView) {
            super(itemView);

            tvSender = (TextView) itemView.findViewById(R.id.tvSender);
            tvOtp = (TextView) itemView.findViewById(R.id.tvOtp);
            tvMessage = (TextView) itemView.findViewById(R.id.tvSmsBody);
            btnDismiss = (ImageButton) itemView.findViewById(R.id.btnDismiss);
            btnCopyOtp = (Button) itemView.findViewById(R.id.btnCopyOtp);

            btnCopyOtp.setOnClickListener(this);
            btnDismiss.setOnClickListener(this);
        }

        public void setModel(OTPModel model) {
            this.model = model;
        }

        public OTPModel getModel() {
            return model;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnDismiss: {
                    deleteModel();

                    Toast.makeText(mContext, "Removed", Toast.LENGTH_SHORT).show();
                }
                break;

                case R.id.btnCopyOtp: {
                    ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText(IConstants.CLIP_DATA, tvOtp.getText().toString());
                    clipboard.setPrimaryClip(clip);

                    Toast.makeText(mContext, "OTP has been copied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }

        public void deleteModel() {
            dismissListener.onItemDelete(model);
            mModels.remove(model);
            notifyDataSetChanged();
        }
    }

    public void clearData() {
        mModels.clear();
        notifyDataSetChanged();
    }
}
