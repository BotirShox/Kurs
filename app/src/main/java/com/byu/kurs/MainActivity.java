package com.byu.kurs;

import android.os.Bundle;
import android.util.Log;
import java.util.Arrays;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.byu.kurs.Adapter.CategoryAdapter;
import com.byu.kurs.Adapter.CourseAdapter;
import com.byu.kurs.model.Category;
import com.byu.kurs.model.Course;
import com.google.android.gms.common.util.Strings;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity <puclic> extends AppCompatActivity {
    private Document doc;
    private Document doc0;
    private Document doc1;
    private Document doc2;
    private Document doc3;
    private Document doc4;
    private Document doc5;
    private Document doc6;
    private Document doc7;
    private Document doc8;
    private Document doc9;
    private Document doc10;
    private Document doc11;
    private Thread secThread;
    private Runnable runnable;
    private ListView listView;
    private CustomArrayAdapter adapter;
    private List<ListItemClass> arrayList;
    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    static CourseAdapter courseAdapter;
    static List<Course> courseList = new ArrayList<>();
    static List<Course> fullCoursesList = new ArrayList<>();
    static List<Category> categoryList = new ArrayList<>();

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init()
    {
        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        adapter = new CustomArrayAdapter(this,R.layout.list_item_1,arrayList,getLayoutInflater());
        listView.setAdapter(adapter);
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        secThread = new Thread(runnable);
        secThread.start();
    }
    private void getWeb()
    {
        try {
            doc = Jsoup.connect("https://eskhata.com/").get();
           // Log.d("Log1", "LogOut1: \n" + rubEs + "\n" + usdEs + "\n" + eurEs);
            Elements tables = doc.getElementsByTag("table");
            Element our_table = tables.get(14);
            for(int i = 0;i < our_table.childrenSize();i++ )
            {
                ListItemClass items = new ListItemClass();
                items.setData_1("?????????????? \n" + our_table.children().get(i).child(0).children().get(0).text().substring(3) + " ????????");
                items.setData_2("?????????? ?????????? ?????? ???????????????? ??????????????????");
                items.setData_3("?????? ?????????? ?????????? ??????");
                items.setData_4("?????????????? / ??????????????");
                arrayList.add(items);
            }
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        categoryList.add(new Category(3, " ", "taj"));
        categoryList.add(new Category(1, " ", "rus"));
        categoryList.add(new Category(2, " ", "uzb"));
        categoryList.add(new Category(4, " ", "kaz"));
        categoryList.add(new Category(5, " ", "kyr"));
        categoryList.add(new Category(6, " ", "arm"));
        categoryList.add(new Category(7, " ", "azer"));
        categoryList.add(new Category(8, " ", "bel"));
        categoryList.add(new Category(9, " ", "mol"));
        setCategoryRecycler(categoryList);
        Element Bm = doc.getElementsByTag("table").get(14).children().get(0);
        String rubBm = Bm.child(3).text().substring(20) + " TJS";
        String usdBm = Bm.child(1).text().substring(14) + " TJS";
        String eurBm = Bm.child(2).text().substring(9) + " TJS";
        courseList.add(new Course(6, "tj", "?????????? ??????????", "\n 1??? =" + rubBm, "1$ =" + usdBm, "1??? = " + eurBm + "\n", "#D0BFF5", "https://www.nbt.tj/ru/", 3));
        Elements Es = doc.getElementsByTag("tr");
        String rubEs = Es.get(52).text().substring(3,10) + " / " + Es.get(52).text().substring(11);
        String eurEs = Es.get(51).text().substring(3,11) + " /" + Es.get(51).text().substring(11);
        String usdEs = Es.get(53).text().substring(3,11) + " / " + Es.get(53).text().substring(12);
        courseList.add(new Course(1, "eskhata", "????????-????????????", "\n??? --" + rubEs, "$ --" + usdEs, "??? --" + eurEs + "\n", "#A9B7EC", "https://play.google.com/store/apps/details?id=com.eskhata.online&hl=ru&gl=US", 3));
        String rubAl = "";
        String usdAl = "";
        String eurAl = "";
        try {
                String[] addr = {"rub", "usd", "eur"};
                for (String l:addr) {
                    String url = "https://alif.tj/api/currency/index.php?currency=" + l + "&date=2025-04-19";
                    doc3 = Jsoup.connect(url).get();
                    String Al = doc3.text();
                    rubAl = Al.substring(29, 35) + " / " + Al.substring(67, 73);
                    usdAl = Al.substring(29, 35) + " / " + Al.substring(68, 74);
                    eurAl = Al.substring(29, 35) + " / " + Al.substring(68, 74);}

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        courseList.add(new Course(2, "alif", "????????-????????", "\n" + "??? -- " + rubAl, "$ -- " + usdAl, "??? -- " + eurAl + "\n", "#FAFBFC", "https://play.google.com/store/apps/details?id=tj.alif.mobi&hl=ru&gl=US", 3));
        try {
            doc8 = Jsoup.connect("https://online.dc.tj/#mtr").get();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        String rubDc = "??? -- " + doc8.getElementsByClass("curr").text().substring(4,11);
        courseList.add(new Course(3, "dc", "Dushanbe City", "\n" + rubDc, "$ -- " + " / ", "??? -- " + " / " + "\n", "#E4F53F", "https://play.google.com/store/apps/details?id=expresspay.wallet&hl=ru&gl=US", 3));
        courseList.add(new Course(5, "orien", "??????????????????\n", "\n" + rubDc, "$ -- " + " / ", "??? -- " + " / " + "\n", "#F5DDBF", "https://play.google.com/store/apps/details?id=tj.orienbank.orienpay&hl=ru&gl=US", 3));
        try {
            doc7 = Jsoup.connect("https://new.amonatbonk.tj/ru/").get();
           // Log.d("Debug1", "Amon1 : \n" + doc7.getElementsByTag("tbody").get(0).getElementsByTag("tr").get(1).text());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements Am = doc7.getElementsByTag("tbody").get(0).getElementsByTag("tr");
        String rubAm = Am.get(3).getElementsByTag("td").get(2).text() + " / " + Am.get(3).getElementsByTag("td").get(3).text();
        String usdAm = Am.get(1).getElementsByTag("td").get(2).text() + " / " + Am.get(1).getElementsByTag("td").get(3).text();
        String eurAm = Am.get(2).getElementsByTag("td").get(2).text() + " / " + Am.get(2).getElementsByTag("td").get(3).text();
        courseList.add(new Course(4,"amonat", "????????????????????", "\n??? -- " + rubAm, "$ -- " + usdAm, "??? -- " + eurAm + "\n", "#C7F6A1", "https://play.google.com/store/apps/details?id=tj.itservice.amonatbonk&hl=ru&gl=US", 3));
        courseList.add(new Course(6, "imon", "???????? ??????????????????\n", "\n??? -- " + rubBm, "$ -- " + " / ", "??? -- " + " / " + "\n", "#F5DDBF", "https://play.google.com/store/apps/details?id=tj.imon.ikfl", 3));
        try {
            doc2 = Jsoup.connect("https://moskva.bankiros.ru/bank/gazprombank/currency").get();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements Sb = doc2.getElementsByClass("table-body").first().getElementsByClass("productBank turn ");
        String usdSb = Sb.first().getElementsByClass("hidden-xs").get(0).text() + " ???";
        String eurSb = Sb.get(1).getElementsByClass("hidden-xs").get(0).text() + " ???";
        String funSb = Sb.get(2).getElementsByClass("hidden-xs").get(0).text() + " ???";
        courseList.add(new Course(6, "sb", "???????? ????????????", "\n1$ = " + usdSb, "1??? = " + eurSb, "1?? = " + funSb + "\n", "#E6E6E6", "https://play.google.com/store/apps/details?id=ru.finassist&hl=ru&gl=US", 1));
        String usdGa = Sb.get(0).getElementsByClass("productBank__cell-with-info-block").get(0).text() + " / " + Sb.get(0).getElementsByClass("productBank__cell-with-info-block").get(1).text();
        String eurGa = Sb.get(1).getElementsByClass("productBank__cell-with-info-block").get(0).text() + " / " + Sb.get(1).getElementsByClass("productBank__cell-with-info-block").get(1).text();
        String funGa = Sb.get(2).getElementsByClass("productBank__cell-with-info-block").get(0).text() + " / " + Sb.get(2).getElementsByClass("productBank__cell-with-info-block").get(1).text();
        courseList.add(new Course(4, "gaz", "??????????????????????", "\n$ -- " + usdGa, "??? -- " + eurGa, "?? -- " + funGa + "\n", "#CFDBE6", "https://play.google.com/store/apps/details?id=ru.gazprombank.android.mobilebank.app&hl=ru&gl=US", 1));
        try {
            doc6 = Jsoup.connect("https://moskva.bankiros.ru/bank/vtb/currency/").get();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements Vt = doc6.getElementsByClass("table-body").first().getElementsByClass("productBank turn ");
        String usdVt = Vt.get(0).getElementsByClass("productBank__cell-with-info-block").get(0).text() + " / " + Vt.get(0).getElementsByClass("productBank__cell-with-info-block").get(1).text();
        String eurVt = Vt.get(1).getElementsByClass("productBank__cell-with-info-block").get(0).text() + " / " + Vt.get(1).getElementsByClass("productBank__cell-with-info-block").get(1).text();
        String funVt = Vt.get(2).getElementsByClass("productBank__cell-with-info-block").get(0).text() + " / " + Vt.get(2).getElementsByClass("productBank__cell-with-info-block").get(1).text();
        courseList.add(new Course(3, "vtb", "????????-??????", "\n$ -- " + usdVt, "??? -- " + eurVt, "?? -- " + funVt + "\n", "#D8F0F5", "https://online.vtb.ru/login", 1));
        try {
            doc0 = Jsoup.connect("https://moskva.bankiros.ru/bank/sberbank/currency").get();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements Sber = doc0.getElementsByClass("table-body").first().getElementsByClass("productBank turn ");
        String Sbusd = Sber.get(0).getElementsByClass("productBank__cell-with-info-block").get(0).text() + " / " + Sber.get(0).getElementsByClass("productBank__cell-with-info-block").get(1).text();
        String Sbeur = Sber.get(1).getElementsByClass("productBank__cell-with-info-block").get(0).text() + " / " + Sber.get(1).getElementsByClass("productBank__cell-with-info-block").get(1).text();
        courseList.add(new Course(1, "sber", "????????????????", "\n$ -- " + Sbusd, "??? -- " + Sbeur + "\n", " ", "#DCFFD7", "https://online.sberbank.ru/CSAFront/index.do", 1));
        try {
            doc1 = Jsoup.connect("https://moskva.bankiros.ru/bank/tcs/currency").get();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements Ti = doc1.getElementsByClass("table-body").first().getElementsByClass("productBank turn ");
        String usdTi = Ti.get(0).getElementsByClass("productBank__cell-with-info-block").get(0).text() + " / " + Ti.get(0).getElementsByClass("productBank__cell-with-info-block").get(1).text();
        String eurTi = Ti.get(1).getElementsByClass("productBank__cell-with-info-block").get(0).text() + " / " + Ti.get(1).getElementsByClass("productBank__cell-with-info-block").get(1).text();
        courseList.add(new Course(2, "tink", "????????????????", "\n$ -- " + usdTi, "??? -- " + eurTi + "\n", " ", "#F7EFB2", "https://play.google.com/store/apps/details?id=com.idamob.tinkoff.android&hl=ru&gl=US", 1));
        courseList.add(new Course(5,"alfa", "??????????-????????\n" , "\n", " ", "\n", "#EFD1C6", "https://alfabank.ru/apps/", 1));
        courseList.add(new Course(6, "nbu", "Milliy Bank", "\n", " ", "\n", "#D0BFF5", "https://play.google.com/store/apps/details?id=com.tune.milliy&hl=ru&gl=US",2));
        try {
            doc9 = Jsoup.connect("https://trustbank.uz/ru/private/money-transfer/").get();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
            } catch (IOException e) {
            e.printStackTrace();
        }
        Elements Mb = doc9.getElementsByClass("exchange-box__table").first().getElementsByTag("tr");
        String usdMb = "1$ = " + Mb.get(1).getElementsByTag("td").get(3).text() + " UZS";
        String eurMb = "1??? = " + Mb.get(2).getElementsByTag("td").get(3).text() + " UZS";
        String rubMb = "1??? = " + Mb.get(6).getElementsByTag("td").get(3).text() + " UZS";
        courseList.add(new Course(1, "mb", "Markaziy Bank", "\n" + usdMb, "" + eurMb,  "" + rubMb + "\n", "#A9B7EC", "https://play.google.com/store/apps/details?id=uz.app.cbu.app&hl=ru&gl=US", 2));
        String usdTr = "$ -- " + Mb.get(1).text().substring(4,15);
        String eurTr = "??? -- " + Mb.get(2).text().substring(4,15);
        String rubTr = "??? -- " + Mb.get(6).text().substring(4,12);
        courseList.add(new Course(2, "tra", "TRASTBABK", "\n" + usdTr, "" + eurTr, "" + rubTr + "\n", "#96E2F7", "https://play.google.com/store/apps/details?id=uz.fido_biznes.trastbank_new&hl=ru&gl=US", 2));
        try {
            doc10 = Jsoup.connect("https://kapitalbank.uz/ru/services/exchange-rates/").get();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements Ka = doc10.getElementsByClass("col-xs-12 col-sm-6 col-md-6 col-lg-3");
        String usdKa = Ka.get(1).text();
        String eurKa = Ka.get(0).text();
        String rubKa = Ka.get(3).text();
        courseList.add(new Course(3, "kap", "KAPITALBANK", "\n" + usdKa, "" + eurKa, "" + rubKa + "\n", "#E4F53F", "https://play.google.com/store/apps/details?id=uz.kapitalbank.kbonline&hl=ru&gl=US", 2));
        try {
            doc11 = Jsoup.connect("https://aab.uz/ru/#").get();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements A = doc11.getElementsByClass("col-xs-3");
        Elements Aa = A.get(0).getElementsByClass("item");
        Elements Aa1 = A.get(1).getElementsByClass("item");
        String usdAa = "$ -- " + Aa.get(1).text() + " / " + Aa1.get(1).text();
        String eurAa = "??? -- " + Aa.get(2).text() + " / " + Aa1.get(2).text();
        String rubAa = "??? -- " + Aa.get(3).text() + " / " + Aa1.get(3).text();
        courseList.add(new Course(4,"aab", "ASIA ALLIANCE BANK", "\n" + usdAa, "" + eurAa, "" + rubAa + "\n", "#C7F6A1", "https://play.google.com/store/apps/details?id=uz.fb.cib.mobile.aab&hl=ru&gl=US", 2));
        courseList.add(new Course(5, "sqb", "Sanoat Qurilish Bank","\n", " ", "\n", "#F5DDBF", "https://play.google.com/store/apps/details?id=uz.fido_biznes.mobile.client.psb&hl=ru&gl=US", 2));

        courseList.add(new Course(1, "kz", "???????????????????????? ????????\n????????????????????","\n", " ", "\n", "#A9B7EC", "https://play.google.com/store/apps/details?id=com.NBKInvestGroupAppsCompany.Investing&hl=ru&gl=US", 4));
        courseList.add(new Course(2, "sber", "????????????????\n??????????????????","\n", " " , "\n", "#96E2F7", "https://www.sber.kz/private_clients/transfers",4));
        courseList.add(new Course(3, "hal", "HALYK BANK","\n", " ", "\n", "#E4F53F", "https://play.google.com/store/apps/details?id=kz.kkb.homebank&hl=ru&gl=US", 4));
        courseList.add(new Course(4,"kaspi", "Kaspi Bank","\n" , " ", "\n", "#C7F6A1", "https://play.google.com/store/apps/details?id=hr.asseco.android.kaspibusiness&hl=ru&gl=US", 4));
        courseList.add(new Course(5, "fort","Forte Bank", "\n", " ", "\n", "#F5DDBF", "https://play.google.com/store/apps/details?id=kz.forte.app.store&hl=ru&gl=US", 4));
        courseList.add(new Course(6, "alfa","??????????-????????\n??????????????????", "\n", " ", "\n", "#D0BFF5", "https://play.google.com/store/apps/details?id=com.base.bankalfalah&hl=ru&gl=US", 4));

        courseList.add(new Course(1, "ki","???????????????????????? ????????\n???????????????????? ????????????????????", "\n", " ", "\n", "#A9B7EC", "https://www.nbkr.kg/index1.jsp?item=1562&lang=RUS", 5));
        courseList.add(new Course(2, "opt","Optima Bank", "\n", " " , "\n", "#96E2F7", "https://play.google.com/store/apps/details?id=kz.optimabank.optima24&hl=ru&gl=US", 5));
        courseList.add(new Course(3, "ayl","???????? ????????", "\n", " ", "\n", "#E4F53F", "https://play.google.com/store/apps/details?id=app.ab.banking&hl=ru&gl=US", 5));
      //  courseList.add(new Course(4, "", "f", "?????? ????????\n" , " ", " ", "#C7F6A1", " ", 5));
      //  courseList.add(new Course(5, "", "f", "?????????? ???????????? \n ???????????????????? ????????", " ", " ", "#F5DDBF", " ", 5));
      //  courseList.add(new Course(6, "", "f", "???? ????????????????????", " ", " ", "#D0BFF5", " ", 5));
        fullCoursesList.addAll(courseList);
        setCourseRecycler(courseList);
    }
    private void setCourseRecycler(List<Course> courseList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager);
        courseAdapter = new CourseAdapter(this, courseList);
        courseRecycler.setAdapter(courseAdapter);
    }
    private void setCategoryRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }
        public static void showCoursesByCategory(int category){
        courseList.clear();
        courseList.addAll(fullCoursesList);
        List<Course> filterCourses = new ArrayList<>();
        for (Course c : courseList) {
            if (c.getCategory() == category)
                filterCourses.add(c);
        }
        courseList.clear();
        courseList.addAll(filterCourses);
        courseAdapter.notifyDataSetChanged();
    }
}