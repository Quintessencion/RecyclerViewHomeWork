package com.example.recyclerviewhomework;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    //Fields
    private List<Pole> list;
    private SimpleDateFormat sdf;


    //Constructor
    public MyAdapter(List<Pole> list) {
        this.list = list;
        sdf = new SimpleDateFormat("dd.MM.yyyy");
    }


    //Functions
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pole pole = list.get(position);
        holder.imagePolicyType.setImageResource(pole.getResIdImagePolicyType());
        holder.imageFollowingTo.setImageResource(pole.getResIdImageNext());

        holder.imageShoppingCart.setImageResource(pole.getResIdImageShoppingCart());
        holder.imageShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pole.setPolicyStartDate(new Date());
                notifyDataSetChanged();
                Toast.makeText(v.getContext(), pole.getPolicyType() + " на " + pole.getObjectOfInsurance() + " продлена", Toast.LENGTH_LONG).show();
            }
        });

        holder.policyType.setText(pole.getPolicyType());
        holder.policyNumber.setText(pole.getPolicyNumber());
        holder.objectOfInsurance.setText(pole.getObjectOfInsurance());
        holder.textRemainingDays.setText(stringConversionTo(pole, holder));
    }

    //установка даты, определения цвета строки и настройки progressbar
    private SpannableStringBuilder stringConversionTo(Pole pole, ViewHolder holder) {
        Date expirationDate = new Date(pole.getPolicyStartDate().getTime() + pole.getValidityPeriodPolicy() * 24 * 60 * 60 * 1000);//Дата окончания страховки
        long restOfDays = (expirationDate.getTime() - System.currentTimeMillis()) / 1000 / 60 / 60 / 24;//оставшиеся дни
        holder.validity.setText("с " + sdf.format(pole.getPolicyStartDate()) + " по " + sdf.format(expirationDate));

        SpannableStringBuilder ssb = new SpannableStringBuilder("Осталось " + restOfDays + " " + getCorrectEnding(restOfDays) + " (" + sdf.format(expirationDate) + ")");

        if (restOfDays > 30) {
            holder.progressBar.setMax((int) ((expirationDate.getTime() - pole.getPolicyStartDate().getTime()) / 1000 / 60 / 60 / 24));
            holder.progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#27c89b"), PorterDuff.Mode.SRC_IN);
            holder.imageShoppingCart.setClickable(false);
            holder.imageShoppingCart.setVisibility(ImageView.INVISIBLE);

        } else if (restOfDays > 7 && restOfDays <= 30) {
            holder.progressBar.setMax(30);
            holder.progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#ff8d3a"), PorterDuff.Mode.SRC_IN);
            holder.imageShoppingCart.setClickable(true);
            holder.imageShoppingCart.setVisibility(ImageView.VISIBLE);
            ForegroundColorSpan style = new ForegroundColorSpan(Color.parseColor("#ff8d3a"));
            ssb.setSpan(style, 9, 16, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        } else if (restOfDays <= 7) {
            holder.progressBar.setMax(7);
            holder.progressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            holder.imageShoppingCart.setClickable(true);
            holder.imageShoppingCart.setVisibility(ImageView.VISIBLE);
            ForegroundColorSpan style = new ForegroundColorSpan(Color.parseColor("#ff563a"));
            ssb.setSpan(style, 9, 16, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        }
        holder.progressBar.setProgress((int) restOfDays);

        return ssb;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //алгоритм падежа
    public String getCorrectEnding(long restOfDays) {
        StringBuilder sb = new StringBuilder();

        for (int i = (int) restOfDays; i > 0; i /= 10) {
            sb.insert(0, i % 10);
        }

        int penultimate = 0;
        int lastNumbSB = Integer.parseInt(sb.substring(sb.length() - 1, sb.length()));
        try {
            penultimate = Integer.parseInt(sb.substring(sb.length() - 2, sb.length() - 1));
        } catch (StringIndexOutOfBoundsException e) {
        }

        if (penultimate == 1 || lastNumbSB == 0 || lastNumbSB >= 5) {
            return "Дней";
        } else if (lastNumbSB == 2 || lastNumbSB == 3 || lastNumbSB == 4) {
            return "Дня";
        } else {
            return "День";
        }
    }


    //Inner class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Fields
        CardView cv;
        ImageView imagePolicyType, imageFollowingTo, imageShoppingCart;
        TextView policyType, policyNumber, objectOfInsurance, validity, textRemainingDays;
        ProgressBar progressBar;

        //Constructor
        public ViewHolder(View itemView) {
            super(itemView);
//            cv = (CardView) itemView.findViewById(R.id.cardView);
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
    }
}
