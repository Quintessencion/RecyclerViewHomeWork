package com.example.recyclerviewhomework;

import android.graphics.PorterDuff;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    //Fields
    List<Pole> list;
    private SimpleDateFormat sdf;
    private final int SKIP = 0;
    private final int DO_NOT_MISS = 1;
    boolean isFilter;


    //Constructor
    public MyAdapter(List<Pole> list) {
        this.list = list;
        sdf = new SimpleDateFormat("dd.MM.yyyy");
    }


    //Functions
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (isFilter) {
            switch (viewType) {
                case SKIP:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
                    break;
                default:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            }
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (isFilter) {
            switch (getItemViewType(position)) {
                case SKIP:
                    holder.init(list.get(position), position);
                    break;
                case DO_NOT_MISS:
                    return;
            }
        } else {
            holder.init(list.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("myLogs", "getItemViewType " + position);
        if (doMissIt(position)) {
            return SKIP;
        } else {
            return DO_NOT_MISS;
        }
    }

    private boolean doMissIt(int position) {
        Date expirationDate = new Date(list.get(position).getPolicyStartDate().getTime() + list.get(position).getValidityPeriodPolicy() * 24 * 60 * 60 * 1000);
        long restOfDays = (expirationDate.getTime() - System.currentTimeMillis()) / 1000 / 60 / 60 / 24;
        return restOfDays <= 7;
    }

    //фильтрация списка
    public void filterListUp() {
        Collections.sort(list, (o1, o2) -> o1.getPolicyStartDate().compareTo(o2.getPolicyStartDate()));
        notifyDataSetChanged();
    }

    public void filterListDown() {
        Collections.sort(list, (o1, o2) -> o2.getPolicyStartDate().compareTo(o1.getPolicyStartDate()));
        notifyDataSetChanged();
    }


    //Inner class
    public class ViewHolder extends RecyclerView.ViewHolder {
        //Fields
        CardView cv;
        ImageView imagePolicyType, imageFollowingTo, imageShoppingCart;
        TextView policyType, policyNumber, objectOfInsurance, validity, textRemainingDays;
        ProgressBar progressBar;


        //Constructor
        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);

            imagePolicyType = (ImageView) itemView.findViewById(R.id.imagePolicyType);
            imageFollowingTo = (ImageView) itemView.findViewById(R.id.imagefollowingTo);
            imageShoppingCart = (ImageView) itemView.findViewById(R.id.imageShoppingCart);

            policyType = (TextView) itemView.findViewById(R.id.policyType);
            policyNumber = (TextView) itemView.findViewById(R.id.policyNumber);
            objectOfInsurance = (TextView) itemView.findViewById(R.id.objectOfInsurance);
            validity = (TextView) itemView.findViewById(R.id.validity);
            textRemainingDays = (TextView) itemView.findViewById(R.id.textRemainingDays);
        }


        //Functions
        //инициализация ViewHolder'а
        private void init(final Pole pole, final int position) {
            imagePolicyType.setImageResource(pole.getResIdImagePolicyType());
            imageFollowingTo.setImageResource(pole.getResIdImageNext());

            imageShoppingCart.setOnClickListener(v -> {
                pole.setPolicyStartDate(new Date());
                notifyItemChanged(position);
                Toast.makeText(v.getContext(), pole.getPolicyType() + " на " + pole.getObjectOfInsurance() + " продлена", Toast.LENGTH_LONG).show();
            });

            policyType.setText(pole.getPolicyType());
            policyNumber.setText(pole.getPolicyNumber());
            objectOfInsurance.setText(pole.getObjectOfInsurance());
            textRemainingDays.setText(stringConversionTo(pole, MyAdapter.ViewHolder.this));
        }

        //установка даты, определения цвета строки и настройки progressbar
        private SpannableStringBuilder stringConversionTo(Pole pole, ViewHolder holder) {
            Date expirationDate = new Date(pole.getPolicyStartDate().getTime() + pole.getValidityPeriodPolicy() * 24 * 60 * 60 * 1000);//Дата окончания страховки
            long restOfDays = (expirationDate.getTime() - System.currentTimeMillis()) / 1000 / 60 / 60 / 24;//оставшиеся дни

            holder.validity.setText("с " + sdf.format(pole.getPolicyStartDate()) + " по " + sdf.format(expirationDate));

            String plural = cv.getResources().getQuantityString(R.plurals.plurals, (int) restOfDays, (int) restOfDays);

            SpannableStringBuilder ssb = new SpannableStringBuilder("Осталось " + plural + " (" + sdf.format(expirationDate) + ")");

            if (restOfDays > 30) {
                int forProgressBarMax = (int) ((expirationDate.getTime() - pole.getPolicyStartDate().getTime()) / 1000 / 60 / 60 / 24);
                setHolderSettings(holder, forProgressBarMax, cv.getResources().getColor(R.color.whiteGreen), ssb, ImageView.INVISIBLE);

            } else if (restOfDays > 7 && restOfDays <= 30) {
                setHolderSettings(holder, 30, cv.getResources().getColor(R.color.orange), ssb, ImageView.VISIBLE);

            } else if (restOfDays <= 7) {
                setHolderSettings(holder, 7, cv.getResources().getColor(R.color.red), ssb, ImageView.VISIBLE);
            }
            holder.progressBar.setProgress((int) restOfDays);

            return ssb;
        }

        //настройка progressBar'а
        private void setHolderSettings(ViewHolder holder, int progressMax, int hexColor, SpannableStringBuilder ssb, int visibility) {
            holder.progressBar.setMax(progressMax);
            holder.progressBar.getProgressDrawable().setColorFilter(hexColor, PorterDuff.Mode.SRC_IN);
            holder.imageShoppingCart.setVisibility(visibility);
            if (progressMax < 30)
                ssb.setSpan(new ForegroundColorSpan(hexColor), 9, 16, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
    }
}
