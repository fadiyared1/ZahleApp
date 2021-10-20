package com.example.menu.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.menu.MainActivity;
import com.example.menu.R;
import com.example.moeidbannerlibrary.banner.BannerLayout;
import com.example.moeidbannerlibrary.banner.BaseBannerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        textView.setText("Zahlé is the capital and the largest city of Beqaa Governorate, Lebanon. With around 150,000 inhabitants, it is the fourth largest city in Lebanon after Beirut, Tripoli, and Sidon and the fourth largest taking the whole urban area.");
        BannerLayout banner=(BannerLayout) root.findViewById(R.id.Banner);

        List<String> urls = new ArrayList<>();
        urls.add("https://i.pinimg.com/originals/be/3e/27/be3e27030a1ee0de33fbebddc71d4262.jpg");
        urls.add("https://mapio.net/images-p/71340630.jpg");
        urls.add("https://www.the961.com/wp-content/uploads/2020/11/Skaff-Park.jpg");
        urls.add("https://i.pinimg.com/564x/0f/ff/b4/0fffb4d6cb6a2b9a78ad8f5bf6c2b7d0.jpg");
        urls.add("https://scontent.fbey14-1.fna.fbcdn.net/v/t31.18172-8/15440585_1202367273132106_6010029110816214885_o.jpg?_nc_cat=101&ccb=1-3&_nc_sid=cdbe9c&_nc_ohc=xGblDq1XCT0AX92LlER&_nc_ht=scontent.fbey14-1.fna&oh=1e50a974fafc45f41f99f7b2f1f61e24&oe=60C76C77");
        urls.add("https://www.the961.com/wp-content/uploads/2020/11/Our-Lady-Of-Zahle.jpg");


        BaseBannerAdapter webBannerAdapter=new BaseBannerAdapter(getContext(),urls);
        webBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position)
            {

            }
        });
        banner.setAdapter(webBannerAdapter);
        return root;
    }
}