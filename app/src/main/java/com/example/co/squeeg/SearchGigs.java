package com.example.co.squeeg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.co.squeeg.Model.GETCategory;
import com.example.co.squeeg.Model.GETCountry;
import com.example.co.squeeg.Model.GETState;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTSubCategory;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user5 on 27-12-2017.
 */

public class SearchGigs extends BaseActivity implements View.OnClickListener {


    private Spinner spinCountry, spinState, spinCategory;
    private GETCountry[] getCountryLists;
    private Gson gson;

    private final List<String> addCountryLists = new ArrayList<String>();
    private final List<GETCategory.Primary> addCategoryLists = new ArrayList<GETCategory.Primary>();
    private final List<POSTSubCategory.Datum> addSubCategoryLists = new ArrayList<POSTSubCategory.Datum>();
    private final List<String> addStateLists = new ArrayList<String>();
    private final List<String> addCatLists = new ArrayList<String>();
    private final List<String> addSubCatLists = new ArrayList<String>();
    private String country = "";
    private String state = "";
    private Button searchGigs;
    private List<GETState> stateData;
    private EditText inputTitle;
    private TextInputLayout tilTitle;
    private TextView tvToolbar;
    private Toolbar mToolbar;
    String cat_id = "";
    private ListView vCategoryList, vSubCategory;
    CountryAdapter aCountryAdapter;
    SubCategory aSubCategory;
    TextView inputChoose_category, inputChoose_country, inputChoose_state, inputChooseSubCategory;
    private AlertDialog mCountryDialog;
    CustomProgressDialog mCustomProgressDialog;
    HashMap<String, String> postCategoryDetails = new HashMap<>();
    public POSTLanguageModel.Search_gigs_screen searchGigsScreen = new POSTLanguageModel().new Search_gigs_screen();
    public POSTLanguageModel.Sell_gigs_screen sellGigsScreen = new POSTLanguageModel().new Sell_gigs_screen();
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_gigs);
        Utils.freeMemory();
        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbar);
        spinCountry = (Spinner) findViewById(R.id.spinner_counrty);
        spinState = (Spinner) findViewById(R.id.spinner_state);
        spinCategory = (Spinner) findViewById(R.id.spinner_category);
        searchGigs = (Button) findViewById(R.id.button_search);
        inputTitle = (EditText) findViewById(R.id.et_title);
        inputChoose_category = (TextView) findViewById(R.id.tv_choose_category);
        inputChoose_country = (TextView) findViewById(R.id.tv_choose_country);
        inputChoose_state = (TextView) findViewById(R.id.tv_choose_state);
        inputChooseSubCategory = (TextView) findViewById(R.id.tv_choose_sub_category);
        tvToolbar = (TextView) findViewById(R.id.tv_toolbar);
        tilTitle = (TextInputLayout) findViewById(R.id.til_title);
        inputChoose_category.setOnClickListener(this);
        inputChooseSubCategory.setOnClickListener(this);
        gson = new Gson();
        setLanguageValues();
        getCategoryLists();

        if (SessionHandler.getInstance().get(SearchGigs.this, AppConstants.COUNTRY_JSON) != null) {

            getCountryLists = gson.fromJson(SessionHandler.getInstance().get(SearchGigs.this, AppConstants.COUNTRY_JSON), GETCountry[].class);

            addCountryLists.add(searchGigsScreen.getLbl_select_country().getName());

            for (GETCountry getCountryList : getCountryLists) {
                addCountryLists.add(getCountryList.getCountry());
            }
            if (addCountryLists.size() > 0) {
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchGigs.this,
                        android.R.layout.simple_spinner_item, addCountryLists);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinCountry.setAdapter(adapter);
            }

            spinCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    state = "";
                    country = "";
                    addStateLists.clear();
                    addStateLists.add(searchGigsScreen.getLbl_select_state().getName());
                    if (position > 0 && getCountryLists.length > 0) {
                        country = getCountryLists[position - 1].getId();
                        if (!country.isEmpty())
                            getStateAPI();
                    } else if (position == 0) {
                        spinState.setAdapter(new ArrayAdapter<String>(SearchGigs.this,
                                android.R.layout.simple_spinner_item, new ArrayList<String>(addStateLists)));
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    }


    private void getCategoryLists() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getCategories(SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<GETCategory>() {
            @Override
            public void onResponse(Call<GETCategory> call, Response<GETCategory> response) {

                if (response.body().getCode().equals(200)) {
                    if (response.body().getPrimary().size() > 0) {
                        GETCategory.Primary data = new GETCategory.Primary();
                        data.setName(searchGigsScreen.getLbl_select_category().getName());
                        addCategoryLists.add(data);
                        for (GETCategory.Primary getCategory : response.body().getPrimary()) {
                            addCatLists.add(getCategory.getName());
                            addCategoryLists.add(getCategory);
                        }

                    } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                        Utils.createUserInActiceAlert(SearchGigs.this, response.body().getMessage());
                    } else {
                        Utils.toastMessage(SearchGigs.this, response.body().getMessage());
                    }

                } else {
                    Utils.toastMessage(SearchGigs.this, response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<GETCategory> call, Throwable t) {
                Log.i("TAG", t.getMessage());
            }
        });
    }

    private void getSubCategory() {
        if (!cat_id.isEmpty()) {
            postCategoryDetails.put("category_id", cat_id);
        }
        if (SessionHandler.getInstance().get(SearchGigs.this, AppConstants.TOKEN_ID) != null) {
            postCategoryDetails.put("user_id", SessionHandler.getInstance().get(SearchGigs.this, AppConstants.TOKEN_ID));
        }
        postCategoryDetails.put("services", "All");
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getSubCategory(postCategoryDetails, SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTSubCategory>() {
            @Override
            public void onResponse(Call<POSTSubCategory> call, final Response<POSTSubCategory> response) {
                if (response.body().getCode().equals(200)) {
                    if (response.body().getData().size() > 0) {
                        if (addSubCategoryLists.size() > 0) {
                            addSubCategoryLists.clear();
                        }

                        POSTSubCategory.Datum datum = new POSTSubCategory.Datum();
                        datum.setName(sellGigsScreen.getLbl_sub_category().getName());
                        addSubCategoryLists.add(datum);

                        for (POSTSubCategory.Datum getCategory : response.body().getData()) {
                            addSubCatLists.add(getCategory.getName());
                            addSubCategoryLists.add(getCategory);
                        }

                    } else {
                        Utils.toastMessage(SearchGigs.this, response.body().getMessage());
                    }

                } else {
                    Utils.toastMessage(SearchGigs.this, response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<POSTSubCategory> call, Throwable t) {
                Log.i("TAG", t.getMessage());

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_choose_category) {
            if (addCategoryLists.size() > 0) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(SearchGigs.this);
                View mShowAddProjectView = getLayoutInflater().inflate(R.layout.dialog_category, null);
                mBuilder.setView(mShowAddProjectView);
                vCategoryList = (ListView) mShowAddProjectView.findViewById(R.id.inputcategorylists);
                aCountryAdapter = new CountryAdapter(SearchGigs.this, addCategoryLists);
                vCategoryList.setAdapter(aCountryAdapter);
                mCountryDialog = mBuilder.create();
                mCountryDialog.show();
            }

        } else if (v.getId() == R.id.tv_choose_sub_category) {
            if (addSubCategoryLists.size() > 0) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(SearchGigs.this);
                View mShowAddProjectView = getLayoutInflater().inflate(R.layout.dialog_sub_category, null);
                mBuilder.setView(mShowAddProjectView);
                vSubCategory = (ListView) mShowAddProjectView.findViewById(R.id.inputsubcategorylists);
                aSubCategory = new SubCategory(SearchGigs.this, addSubCategoryLists);
                vSubCategory.setAdapter(aSubCategory);
                mCountryDialog = mBuilder.create();
                mCountryDialog.show();
            }
        }
    }

    public class CountryAdapter extends BaseAdapter {

        Context mContext;
        LayoutInflater mInflater;
        private List<GETCategory.Primary> mCountry = new ArrayList<>();

        public CountryAdapter(Context mContext, List<GETCategory.Primary> mCountry) {
            this.mContext = mContext;
            this.mCountry = mCountry;
            mInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mCountry.size();
        }

        public Object getItem(int position) {
            return mCountry.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mHolder;
            if (convertView == null) {
                mHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.adapter_labels, null);
                mHolder.mName = (TextView) convertView.findViewById(R.id.inputName);
                convertView.setTag(mHolder);
            } else {
                mHolder = (ViewHolder) convertView.getTag();
            }

            String name = mCountry.get(position).getName();
            mHolder.mName.setText(mCountry.get(position).getName());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mCountry.get(position).getName().equalsIgnoreCase(searchGigsScreen.getLbl_select_category().getName())) {
                        inputChoose_category.setText(mCountry.get(position).getName());
                        cat_id = "";
                        inputChooseSubCategory.setVisibility(View.GONE);
                    } else {
                        inputChoose_category.setText(mCountry.get(position).getName());
                        cat_id = mCountry.get(position).getCid();
                        if (mCountry.get(position).getSubcategory().equalsIgnoreCase("1")) {
                            inputChooseSubCategory.setVisibility(View.VISIBLE);
                            getSubCategory();
                        } else {
                            inputChooseSubCategory.setVisibility(View.GONE);
                        }
                    }
                    mCountryDialog.dismiss();
                }
            });
            return convertView;
        }

    }

    private class ViewHolder {
        TextView mName;
    }

    private void getStateAPI() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getState(country, AppConstants.GUEST_TOKEN, SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<List<GETState>>() {
            @Override
            public void onResponse(Call<List<GETState>> call, final Response<List<GETState>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        stateData = response.body();
                        addStateLists.clear();
                        addStateLists.add(searchGigsScreen.getLbl_select_state().getName());
                        for (GETState getState : response.body()) {
                            addStateLists.add(getState.getState_name());
                        }
                        if (addStateLists.size() > 0 && stateData.size() > 0) {
                            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchGigs.this,
                                    android.R.layout.simple_spinner_item, addStateLists);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinState.setAdapter(adapter);

                            spinState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (position > 0 && stateData.size() > 0) {
                                        state = stateData.get(position - 1).getState_id();
                                    } else if (position == 0) {
                                        state = "";
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<List<GETState>> call, Throwable t) {
                Log.i("TAG", t.getMessage());
            }
        });
    }

    public void SearchGigs(View view) {

        if (cat_id.isEmpty() && state.isEmpty() && country.isEmpty() && inputTitle.getText().toString().isEmpty()) {
            Toast.makeText(this, commonStrings.getLbl_select_any_one_field().getName(), Toast.LENGTH_SHORT).show();
        } else {
            Intent callSearchActivity = new Intent(SearchGigs.this, SearchGigsList.class);
            callSearchActivity.putExtra("cat_id", cat_id);
            callSearchActivity.putExtra("state", state);
            callSearchActivity.putExtra("country", country);
            callSearchActivity.putExtra("title", inputTitle.getText().toString());
            startActivity(callSearchActivity);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            Intent gotoMain = new Intent(SearchGigs.this, MainActivity.class);
            startActivity(gotoMain);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public class SubCategory extends BaseAdapter {

        Context mContext;
        LayoutInflater mInflater;
        private List<POSTSubCategory.Datum> mCountry = new ArrayList<>();

        public SubCategory(Context mContext, List<POSTSubCategory.Datum> mCountry) {
            this.mContext = mContext;
            this.mCountry = mCountry;
            mInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mCountry.size();
        }

        public Object getItem(int position) {
            return mCountry.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolderSubCategory mHolder;
            if (convertView == null) {
                mHolder = new ViewHolderSubCategory();
                convertView = mInflater.inflate(R.layout.adapter_labels, null);
                mHolder.mName = (TextView) convertView.findViewById(R.id.inputName);
                convertView.setTag(mHolder);
            } else {
                mHolder = (ViewHolderSubCategory) convertView.getTag();
            }

            String name = mCountry.get(position).getName();
            mHolder.mName.setText(mCountry.get(position).getName());


            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCountry.get(position).getName().equalsIgnoreCase(getResources().getString(R.string.select_sub_category))) {
                        inputChooseSubCategory.setText(mCountry.get(position).getName());
                        cat_id = "";
                    } else {
                        inputChooseSubCategory.setText(mCountry.get(position).getName());
                        cat_id = mCountry.get(position).getCid();
                    }
                    mCountryDialog.dismiss();

                }
            });
            return convertView;
        }


    }

    private class ViewHolderSubCategory {
        TextView mName;
    }

    public void setLanguageValues() {
        searchGigsScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.SEARCHGIGS), POSTLanguageModel.Search_gigs_screen.class);
        sellGigsScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.SELLGIGS), POSTLanguageModel.Sell_gigs_screen.class);
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        tilTitle.setHint(searchGigsScreen.getTxt_fld_title().getName());
        inputChoose_category.setText(searchGigsScreen.getLbl_select_category().getName());
        inputChooseSubCategory.setText(sellGigsScreen.getLbl_sub_category().getName());
        searchGigs.setText(searchGigsScreen.getLbl_search_gigs().getPlaceholder());
        tvToolbar.setText(searchGigsScreen.getLbl_search_gigs().getName());
    }

}
