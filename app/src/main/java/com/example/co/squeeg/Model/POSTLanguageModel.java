package com.example.co.squeeg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POSTLanguageModel {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Register_screen {

        @SerializedName("btn_register")
        @Expose
        private Btn_register btn_register;
        @SerializedName("btn_submit")
        @Expose
        private Btn_submit_ btn_submit;
        @SerializedName("btn_terms_and_conditions")
        @Expose
        private Btn_terms_and_conditions btn_terms_and_conditions;
        @SerializedName("lbl_already_mem")
        @Expose
        private Lbl_already_mem lbl_already_mem;
        @SerializedName("lbl_country")
        @Expose
        private Lbl_country_ lbl_country;
        @SerializedName("lbl_login")
        @Expose
        private Lbl_login_ lbl_login;
        @SerializedName("lbl_register")
        @Expose
        private Lbl_register lbl_register;
        @SerializedName("lbl_select_category")
        @Expose
        private Lbl_select_category_ lbl_select_category;
        @SerializedName("lbl_select_lang")
        @Expose
        private Lbl_select_lang lbl_select_lang;
        @SerializedName("lbl_state")
        @Expose
        private Lbl_state lbl_state;
        @SerializedName("lbl_terms_and_conditions")
        @Expose
        private Lbl_terms_and_conditions lbl_terms_and_conditions;
        @SerializedName("txt_fld_address_line")
        @Expose
        private Txt_fld_address_line txt_fld_address_line;
        @SerializedName("txt_fld_city")
        @Expose
        private Txt_fld_city txt_fld_city;
        @SerializedName("txt_fld_email")
        @Expose
        private Txt_fld_email_ txt_fld_email;
        @SerializedName("txt_fld_fullname")
        @Expose
        private Txt_fld_fullname txt_fld_fullname;
        @SerializedName("txt_fld_ph_num")
        @Expose
        private Txt_fld_ph_num txt_fld_ph_num;
        @SerializedName("txt_fld_pwd")
        @Expose
        private Txt_fld_pwd_ txt_fld_pwd;
        @SerializedName("txt_fld_rpwd")
        @Expose
        private Txt_fld_rpwd txt_fld_rpwd;
        @SerializedName("txt_fld_suggestions")
        @Expose
        private Txt_fld_suggestions txt_fld_suggestions;
        @SerializedName("txt_fld_username")
        @Expose
        private Txt_fld_username_ txt_fld_username;

        @SerializedName("lbl_fld_zipcode")
        @Expose
        private Lbl_fld_zipcode lbl_fld_zipcode;

        @SerializedName("lbl_update_profile")
        @Expose
        private Lbl_update_profile lbl_update_profile;

        public Lbl_update_profile getLbl_update_profile() {
            return lbl_update_profile;
        }

        public void setLbl_update_profile(Lbl_update_profile lbl_update_profile) {
            this.lbl_update_profile = lbl_update_profile;
        }

        public Lbl_fld_zipcode getLbl_fld_zipcode() {
            return lbl_fld_zipcode;
        }

        public void setLbl_fld_zipcode(Lbl_fld_zipcode lbl_fld_zipcode) {
            this.lbl_fld_zipcode = lbl_fld_zipcode;
        }

        public Btn_register getBtn_register() {
            return btn_register;
        }

        public void setBtn_register(Btn_register btn_register) {
            this.btn_register = btn_register;
        }

        public Btn_submit_ getBtn_submit() {
            return btn_submit;
        }

        public void setBtn_submit(Btn_submit_ btn_submit) {
            this.btn_submit = btn_submit;
        }

        public Btn_terms_and_conditions getBtn_terms_and_conditions() {
            return btn_terms_and_conditions;
        }

        public void setBtn_terms_and_conditions(Btn_terms_and_conditions btn_terms_and_conditions) {
            this.btn_terms_and_conditions = btn_terms_and_conditions;
        }

        public Lbl_already_mem getLbl_already_mem() {
            return lbl_already_mem;
        }

        public void setLbl_already_mem(Lbl_already_mem lbl_already_mem) {
            this.lbl_already_mem = lbl_already_mem;
        }

        public Lbl_country_ getLbl_country() {
            return lbl_country;
        }

        public void setLbl_country(Lbl_country_ lbl_country) {
            this.lbl_country = lbl_country;
        }

        public Lbl_login_ getLbl_login() {
            return lbl_login;
        }

        public void setLbl_login(Lbl_login_ lbl_login) {
            this.lbl_login = lbl_login;
        }

        public Lbl_register getLbl_register() {
            return lbl_register;
        }

        public void setLbl_register(Lbl_register lbl_register) {
            this.lbl_register = lbl_register;
        }

        public Lbl_select_category_ getLbl_select_category() {
            return lbl_select_category;
        }

        public void setLbl_select_category(Lbl_select_category_ lbl_select_category) {
            this.lbl_select_category = lbl_select_category;
        }

        public Lbl_select_lang getLbl_select_lang() {
            return lbl_select_lang;
        }

        public void setLbl_select_lang(Lbl_select_lang lbl_select_lang) {
            this.lbl_select_lang = lbl_select_lang;
        }

        public Lbl_state getLbl_state() {
            return lbl_state;
        }

        public void setLbl_state(Lbl_state lbl_state) {
            this.lbl_state = lbl_state;
        }

        public Lbl_terms_and_conditions getLbl_terms_and_conditions() {
            return lbl_terms_and_conditions;
        }

        public void setLbl_terms_and_conditions(Lbl_terms_and_conditions lbl_terms_and_conditions) {
            this.lbl_terms_and_conditions = lbl_terms_and_conditions;
        }

        public Txt_fld_address_line getTxt_fld_address_line() {
            return txt_fld_address_line;
        }

        public void setTxt_fld_address_line(Txt_fld_address_line txt_fld_address_line) {
            this.txt_fld_address_line = txt_fld_address_line;
        }

        public Txt_fld_city getTxt_fld_city() {
            return txt_fld_city;
        }

        public void setTxt_fld_city(Txt_fld_city txt_fld_city) {
            this.txt_fld_city = txt_fld_city;
        }

        public Txt_fld_email_ getTxt_fld_email() {
            return txt_fld_email;
        }

        public void setTxt_fld_email(Txt_fld_email_ txt_fld_email) {
            this.txt_fld_email = txt_fld_email;
        }

        public Txt_fld_fullname getTxt_fld_fullname() {
            return txt_fld_fullname;
        }

        public void setTxt_fld_fullname(Txt_fld_fullname txt_fld_fullname) {
            this.txt_fld_fullname = txt_fld_fullname;
        }

        public Txt_fld_ph_num getTxt_fld_ph_num() {
            return txt_fld_ph_num;
        }

        public void setTxt_fld_ph_num(Txt_fld_ph_num txt_fld_ph_num) {
            this.txt_fld_ph_num = txt_fld_ph_num;
        }

        public Txt_fld_pwd_ getTxt_fld_pwd() {
            return txt_fld_pwd;
        }

        public void setTxt_fld_pwd(Txt_fld_pwd_ txt_fld_pwd) {
            this.txt_fld_pwd = txt_fld_pwd;
        }

        public Txt_fld_rpwd getTxt_fld_rpwd() {
            return txt_fld_rpwd;
        }

        public void setTxt_fld_rpwd(Txt_fld_rpwd txt_fld_rpwd) {
            this.txt_fld_rpwd = txt_fld_rpwd;
        }

        public Txt_fld_suggestions getTxt_fld_suggestions() {
            return txt_fld_suggestions;
        }

        public void setTxt_fld_suggestions(Txt_fld_suggestions txt_fld_suggestions) {
            this.txt_fld_suggestions = txt_fld_suggestions;
        }

        public Txt_fld_username_ getTxt_fld_username() {
            return txt_fld_username;
        }

        public void setTxt_fld_username(Txt_fld_username_ txt_fld_username) {
            this.txt_fld_username = txt_fld_username;
        }

    }


    public class Search_gigs_screen {

        @SerializedName("lbl_search_gigs")
        @Expose
        private Lbl_search_gigs_ lbl_search_gigs;
        @SerializedName("lbl_select_category")
        @Expose
        private Lbl_select_category__ lbl_select_category;
        @SerializedName("lbl_select_country")
        @Expose
        private Lbl_select_country lbl_select_country;
        @SerializedName("lbl_select_state")
        @Expose
        private Lbl_select_state lbl_select_state;
        @SerializedName("txt_fld_title")
        @Expose
        private Txt_fld_title txt_fld_title;

        public Lbl_search_gigs_ getLbl_search_gigs() {
            return lbl_search_gigs;
        }

        public void setLbl_search_gigs(Lbl_search_gigs_ lbl_search_gigs) {
            this.lbl_search_gigs = lbl_search_gigs;
        }

        public Lbl_select_category__ getLbl_select_category() {
            return lbl_select_category;
        }

        public void setLbl_select_category(Lbl_select_category__ lbl_select_category) {
            this.lbl_select_category = lbl_select_category;
        }

        public Lbl_select_country getLbl_select_country() {
            return lbl_select_country;
        }

        public void setLbl_select_country(Lbl_select_country lbl_select_country) {
            this.lbl_select_country = lbl_select_country;
        }

        public Lbl_select_state getLbl_select_state() {
            return lbl_select_state;
        }

        public void setLbl_select_state(Lbl_select_state lbl_select_state) {
            this.lbl_select_state = lbl_select_state;
        }

        public Txt_fld_title getTxt_fld_title() {
            return txt_fld_title;
        }

        public void setTxt_fld_title(Txt_fld_title txt_fld_title) {
            this.txt_fld_title = txt_fld_title;
        }

    }


    public class Sell_gigs_screen {

        @SerializedName("btn_create_gig")
        @Expose
        private Btn_create_gig btn_create_gig;
        @SerializedName("lbl_add_more_items")
        @Expose
        private Lbl_add_more_items lbl_add_more_items;
        @SerializedName("lbl_day")
        @Expose
        private Lbl_day lbl_day;
        @SerializedName("lbl_earn_money")
        @Expose
        private Lbl_earn_money lbl_earn_money;
        @SerializedName("lbl_Ican")
        @Expose
        private Lbl_Ican lbl_Ican;
        @SerializedName("lbl_onsite")
        @Expose
        private Lbl_onsite lbl_onsite;
        @SerializedName("lbl_remote")
        @Expose
        private Lbl_remote lbl_remote;
        @SerializedName("lbl_select_category")
        @Expose
        private Lbl_select_category___ lbl_select_category;
        @SerializedName("lbl_sub_category")
        @Expose
        private Lbl_sub_category lbl_sub_category;
        @SerializedName("lbl_super_fast_delivery")
        @Expose
        private Lbl_super_fast_delivery lbl_super_fast_delivery;
        @SerializedName("lbl_terms_condition")
        @Expose
        private Lbl_terms_condition lbl_terms_condition;
        @SerializedName("lbl_work_option")
        @Expose
        private Lbl_work_option lbl_work_option;
        @SerializedName("txt_fld_buyer_needs")
        @Expose
        private Txt_fld_buyer_needs txt_fld_buyer_needs;
        @SerializedName("txt_fld_deliver_gig")
        @Expose
        private Txt_fld_deliver_gig txt_fld_deliver_gig;
        @SerializedName("txt_fld_gig_cost")
        @Expose
        private Txt_fld_gig_cost txt_fld_gig_cost;
        @SerializedName("txt_fld_provide_info")
        @Expose
        private Txt_fld_provide_info txt_fld_provide_info;
        @SerializedName("txt_fld_title_gigs")
        @Expose
        private Txt_fld_title_gigs txt_fld_title_gigs;

        @SerializedName("err_select_gig_image")
        @Expose
        private Err_select_gig_image err_select_gig_image;


        @SerializedName("lbl_update_a_gig")
        @Expose
        private Lbl_update_a_gig lbl_update_a_gig;

        public Lbl_update_a_gig getLbl_update_a_gig() {
            return lbl_update_a_gig;
        }

        public void setLbl_update_a_gig(Lbl_update_a_gig lbl_update_a_gig) {
            this.lbl_update_a_gig = lbl_update_a_gig;
        }

        public Err_select_gig_image getErr_select_gig_image() {
            return err_select_gig_image;
        }

        public void setErr_select_gig_image(Err_select_gig_image err_select_gig_image) {
            this.err_select_gig_image = err_select_gig_image;
        }

        public Btn_create_gig getBtn_create_gig() {
            return btn_create_gig;
        }

        public void setBtn_create_gig(Btn_create_gig btn_create_gig) {
            this.btn_create_gig = btn_create_gig;
        }

        public Lbl_add_more_items getLbl_add_more_items() {
            return lbl_add_more_items;
        }

        public void setLbl_add_more_items(Lbl_add_more_items lbl_add_more_items) {
            this.lbl_add_more_items = lbl_add_more_items;
        }

        public Lbl_day getLbl_day() {
            return lbl_day;
        }

        public void setLbl_day(Lbl_day lbl_day) {
            this.lbl_day = lbl_day;
        }

        public Lbl_earn_money getLbl_earn_money() {
            return lbl_earn_money;
        }

        public void setLbl_earn_money(Lbl_earn_money lbl_earn_money) {
            this.lbl_earn_money = lbl_earn_money;
        }

        public Lbl_Ican getLbl_Ican() {
            return lbl_Ican;
        }

        public void setLbl_Ican(Lbl_Ican lbl_Ican) {
            this.lbl_Ican = lbl_Ican;
        }

        public Lbl_onsite getLbl_onsite() {
            return lbl_onsite;
        }

        public void setLbl_onsite(Lbl_onsite lbl_onsite) {
            this.lbl_onsite = lbl_onsite;
        }

        public Lbl_remote getLbl_remote() {
            return lbl_remote;
        }

        public void setLbl_remote(Lbl_remote lbl_remote) {
            this.lbl_remote = lbl_remote;
        }

        public Lbl_select_category___ getLbl_select_category() {
            return lbl_select_category;
        }

        public void setLbl_select_category(Lbl_select_category___ lbl_select_category) {
            this.lbl_select_category = lbl_select_category;
        }

        public Lbl_sub_category getLbl_sub_category() {
            return lbl_sub_category;
        }

        public void setLbl_sub_category(Lbl_sub_category lbl_sub_category) {
            this.lbl_sub_category = lbl_sub_category;
        }

        public Lbl_super_fast_delivery getLbl_super_fast_delivery() {
            return lbl_super_fast_delivery;
        }

        public void setLbl_super_fast_delivery(Lbl_super_fast_delivery lbl_super_fast_delivery) {
            this.lbl_super_fast_delivery = lbl_super_fast_delivery;
        }

        public Lbl_terms_condition getLbl_terms_condition() {
            return lbl_terms_condition;
        }

        public void setLbl_terms_condition(Lbl_terms_condition lbl_terms_condition) {
            this.lbl_terms_condition = lbl_terms_condition;
        }

        public Lbl_work_option getLbl_work_option() {
            return lbl_work_option;
        }

        public void setLbl_work_option(Lbl_work_option lbl_work_option) {
            this.lbl_work_option = lbl_work_option;
        }

        public Txt_fld_buyer_needs getTxt_fld_buyer_needs() {
            return txt_fld_buyer_needs;
        }

        public void setTxt_fld_buyer_needs(Txt_fld_buyer_needs txt_fld_buyer_needs) {
            this.txt_fld_buyer_needs = txt_fld_buyer_needs;
        }

        public Txt_fld_deliver_gig getTxt_fld_deliver_gig() {
            return txt_fld_deliver_gig;
        }

        public void setTxt_fld_deliver_gig(Txt_fld_deliver_gig txt_fld_deliver_gig) {
            this.txt_fld_deliver_gig = txt_fld_deliver_gig;
        }

        public Txt_fld_gig_cost getTxt_fld_gig_cost() {
            return txt_fld_gig_cost;
        }

        public void setTxt_fld_gig_cost(Txt_fld_gig_cost txt_fld_gig_cost) {
            this.txt_fld_gig_cost = txt_fld_gig_cost;
        }

        public Txt_fld_provide_info getTxt_fld_provide_info() {
            return txt_fld_provide_info;
        }

        public void setTxt_fld_provide_info(Txt_fld_provide_info txt_fld_provide_info) {
            this.txt_fld_provide_info = txt_fld_provide_info;
        }

        public Txt_fld_title_gigs getTxt_fld_title_gigs() {
            return txt_fld_title_gigs;
        }

        public void setTxt_fld_title_gigs(Txt_fld_title_gigs txt_fld_title_gigs) {
            this.txt_fld_title_gigs = txt_fld_title_gigs;
        }

    }


    public class Settings_screen {

        @SerializedName("lbl_arabic")
        @Expose
        private Lbl_arabic lbl_arabic;
        @SerializedName("lbl_change_language")
        @Expose
        private Lbl_change_language lbl_change_language;
        @SerializedName("lbl_change_pwd")
        @Expose
        private Lbl_change_pwd lbl_change_pwd;
        @SerializedName("lbl_choose_lang")
        @Expose
        private Lbl_choose_lang lbl_choose_lang;
        @SerializedName("lbl_edit_profile")
        @Expose
        private Lbl_edit_profile lbl_edit_profile;
        @SerializedName("lbl_english")
        @Expose
        private Lbl_english lbl_english;
        @SerializedName("lbl_help_and_support")
        @Expose
        private Lbl_help_and_support lbl_help_and_support;
        @SerializedName("lbl_logout")
        @Expose
        private Lbl_logout lbl_logout;
        @SerializedName("lbl_no")
        @Expose
        private Lbl_no lbl_no;
        @SerializedName("lbl_settings")
        @Expose
        private Lbl_settings lbl_settings;
        @SerializedName("lbl_wallet")
        @Expose
        private Lbl_wallet lbl_wallet;
        @SerializedName("lbl_yes")
        @Expose
        private Lbl_yes lbl_yes;
        @SerializedName("txt_logout_info")
        @Expose
        private Txt_logout_info txt_logout_info;

        public Lbl_arabic getLbl_arabic() {
            return lbl_arabic;
        }

        public void setLbl_arabic(Lbl_arabic lbl_arabic) {
            this.lbl_arabic = lbl_arabic;
        }

        public Lbl_change_language getLbl_change_language() {
            return lbl_change_language;
        }

        public void setLbl_change_language(Lbl_change_language lbl_change_language) {
            this.lbl_change_language = lbl_change_language;
        }

        public Lbl_change_pwd getLbl_change_pwd() {
            return lbl_change_pwd;
        }

        public void setLbl_change_pwd(Lbl_change_pwd lbl_change_pwd) {
            this.lbl_change_pwd = lbl_change_pwd;
        }

        public Lbl_choose_lang getLbl_choose_lang() {
            return lbl_choose_lang;
        }

        public void setLbl_choose_lang(Lbl_choose_lang lbl_choose_lang) {
            this.lbl_choose_lang = lbl_choose_lang;
        }

        public Lbl_edit_profile getLbl_edit_profile() {
            return lbl_edit_profile;
        }

        public void setLbl_edit_profile(Lbl_edit_profile lbl_edit_profile) {
            this.lbl_edit_profile = lbl_edit_profile;
        }

        public Lbl_english getLbl_english() {
            return lbl_english;
        }

        public void setLbl_english(Lbl_english lbl_english) {
            this.lbl_english = lbl_english;
        }

        public Lbl_help_and_support getLbl_help_and_support() {
            return lbl_help_and_support;
        }

        public void setLbl_help_and_support(Lbl_help_and_support lbl_help_and_support) {
            this.lbl_help_and_support = lbl_help_and_support;
        }

        public Lbl_logout getLbl_logout() {
            return lbl_logout;
        }

        public void setLbl_logout(Lbl_logout lbl_logout) {
            this.lbl_logout = lbl_logout;
        }

        public Lbl_no getLbl_no() {
            return lbl_no;
        }

        public void setLbl_no(Lbl_no lbl_no) {
            this.lbl_no = lbl_no;
        }

        public Lbl_settings getLbl_settings() {
            return lbl_settings;
        }

        public void setLbl_settings(Lbl_settings lbl_settings) {
            this.lbl_settings = lbl_settings;
        }

        public Lbl_wallet getLbl_wallet() {
            return lbl_wallet;
        }

        public void setLbl_wallet(Lbl_wallet lbl_wallet) {
            this.lbl_wallet = lbl_wallet;
        }

        public Lbl_yes getLbl_yes() {
            return lbl_yes;
        }

        public void setLbl_yes(Lbl_yes lbl_yes) {
            this.lbl_yes = lbl_yes;
        }

        public Txt_logout_info getTxt_logout_info() {
            return txt_logout_info;
        }

        public void setTxt_logout_info(Txt_logout_info txt_logout_info) {
            this.txt_logout_info = txt_logout_info;
        }

    }


    public class Stripe_payment_screen {

        @SerializedName("btn_save")
        @Expose
        private Btn_save btn_save;
        @SerializedName("btn_update")
        @Expose
        private Btn_update btn_update;
        @SerializedName("txt_fld_acc_name")
        @Expose
        private Txt_fld_acc_name txt_fld_acc_name;
        @SerializedName("txt_fld_acc_num")
        @Expose
        private Txt_fld_acc_num txt_fld_acc_num;
        @SerializedName("txt_fld_bank_addr")
        @Expose
        private Txt_fld_bank_addr txt_fld_bank_addr;
        @SerializedName("txt_fld_bank_name")
        @Expose
        private Txt_fld_bank_name txt_fld_bank_name;
        @SerializedName("txt_fld_IBan")
        @Expose
        private Txt_fld_IBan txt_fld_IBan;
        @SerializedName("txt_fld_ifsc_code")
        @Expose
        private Txt_fld_ifsc_code txt_fld_ifsc_code;
        @SerializedName("txt_fld_sort_code")
        @Expose
        private Txt_fld_sort_code txt_fld_sort_code;
        @SerializedName("txt_fld_swift_num")
        @Expose
        private Txt_fld_swift_num txt_fld_swift_num;

        public Btn_save getBtn_save() {
            return btn_save;
        }

        public void setBtn_save(Btn_save btn_save) {
            this.btn_save = btn_save;
        }

        public Btn_update getBtn_update() {
            return btn_update;
        }

        public void setBtn_update(Btn_update btn_update) {
            this.btn_update = btn_update;
        }

        public Txt_fld_acc_name getTxt_fld_acc_name() {
            return txt_fld_acc_name;
        }

        public void setTxt_fld_acc_name(Txt_fld_acc_name txt_fld_acc_name) {
            this.txt_fld_acc_name = txt_fld_acc_name;
        }

        public Txt_fld_acc_num getTxt_fld_acc_num() {
            return txt_fld_acc_num;
        }

        public void setTxt_fld_acc_num(Txt_fld_acc_num txt_fld_acc_num) {
            this.txt_fld_acc_num = txt_fld_acc_num;
        }

        public Txt_fld_bank_addr getTxt_fld_bank_addr() {
            return txt_fld_bank_addr;
        }

        public void setTxt_fld_bank_addr(Txt_fld_bank_addr txt_fld_bank_addr) {
            this.txt_fld_bank_addr = txt_fld_bank_addr;
        }

        public Txt_fld_bank_name getTxt_fld_bank_name() {
            return txt_fld_bank_name;
        }

        public void setTxt_fld_bank_name(Txt_fld_bank_name txt_fld_bank_name) {
            this.txt_fld_bank_name = txt_fld_bank_name;
        }

        public Txt_fld_IBan getTxt_fld_IBan() {
            return txt_fld_IBan;
        }

        public void setTxt_fld_IBan(Txt_fld_IBan txt_fld_IBan) {
            this.txt_fld_IBan = txt_fld_IBan;
        }

        public Txt_fld_ifsc_code getTxt_fld_ifsc_code() {
            return txt_fld_ifsc_code;
        }

        public void setTxt_fld_ifsc_code(Txt_fld_ifsc_code txt_fld_ifsc_code) {
            this.txt_fld_ifsc_code = txt_fld_ifsc_code;
        }

        public Txt_fld_sort_code getTxt_fld_sort_code() {
            return txt_fld_sort_code;
        }

        public void setTxt_fld_sort_code(Txt_fld_sort_code txt_fld_sort_code) {
            this.txt_fld_sort_code = txt_fld_sort_code;
        }

        public Txt_fld_swift_num getTxt_fld_swift_num() {
            return txt_fld_swift_num;
        }

        public void setTxt_fld_swift_num(Txt_fld_swift_num txt_fld_swift_num) {
            this.txt_fld_swift_num = txt_fld_swift_num;
        }

    }


    public class Tabbar_screen {

        @SerializedName("lbl_buy")
        @Expose
        private Lbl_buy lbl_buy;
        @SerializedName("lbl_chat")
        @Expose
        private Lbl_chat lbl_chat;
        @SerializedName("lbl_home")
        @Expose
        private Lbl_home lbl_home;
        @SerializedName("lbl_sell")
        @Expose
        private Lbl_sell lbl_sell;
        @SerializedName("lbl_settings")
        @Expose
        private Lbl_settings_ lbl_settings;
        @SerializedName("lbl_offer")
        @Expose
        private Lbl_offer lbl_offer;
        @SerializedName("lbl_request")
        @Expose
        private Lbl_request lbl_request;

        public Lbl_buy getLbl_buy() {
            return lbl_buy;
        }

        public void setLbl_buy(Lbl_buy lbl_buy) {
            this.lbl_buy = lbl_buy;
        }

        public Lbl_chat getLbl_chat() {
            return lbl_chat;
        }

        public void setLbl_chat(Lbl_chat lbl_chat) {
            this.lbl_chat = lbl_chat;
        }

        public Lbl_home getLbl_home() {
            return lbl_home;
        }

        public void setLbl_home(Lbl_home lbl_home) {
            this.lbl_home = lbl_home;
        }

        public Lbl_sell getLbl_sell() {
            return lbl_sell;
        }

        public void setLbl_sell(Lbl_sell lbl_sell) {
            this.lbl_sell = lbl_sell;
        }

        public Lbl_settings_ getLbl_settings() {
            return lbl_settings;
        }

        public void setLbl_settings(Lbl_settings_ lbl_settings) {
            this.lbl_settings = lbl_settings;
        }

        public Lbl_offer getLbl_offer() {
            return lbl_offer;
        }

        public void setLbl_offer(Lbl_offer lbl_offer) {
            this.lbl_offer = lbl_offer;
        }

        public Lbl_request getLbl_request() {
            return lbl_request;
        }

        public void setLbl_request(Lbl_request lbl_request) {
            this.lbl_request = lbl_request;
        }
    }


    public class Common_js {

        @SerializedName("lg_error_occured")
        @Expose
        private Lg_error_occured lg_error_occured;

        public Lg_error_occured getLg_error_occured() {
            return lg_error_occured;
        }

        public void setLg_error_occured(Lg_error_occured lg_error_occured) {
            this.lg_error_occured = lg_error_occured;
        }

    }

    public class Common_strings {

        @SerializedName("lbl_add_photo")
        @Expose
        private Lbl_add_photo lbl_add_photo;
        @SerializedName("lbl_cancel")
        @Expose
        private Lbl_cancel lbl_cancel;
        @SerializedName("lbl_enable_internet")
        @Expose
        private Lbl_enable_internet lbl_enable_internet;
        @SerializedName("lbl_guest")
        @Expose
        private Lbl_guest lbl_guest;
        @SerializedName("lbl_network_err")
        @Expose
        private Lbl_network_err lbl_network_err;
        @SerializedName("lbl_no_chat")
        @Expose
        private Lbl_no_chat lbl_no_chat;
        @SerializedName("lbl_no_data_found")
        @Expose
        private Lbl_no_data_found lbl_no_data_found;
        @SerializedName("lbl_no_fav_available")
        @Expose
        private Lbl_no_fav_available lbl_no_fav_available;
        @SerializedName("lbl_no_feedback_found")
        @Expose
        private Lbl_no_feedback_found lbl_no_feedback_found;
        @SerializedName("lbl_no_gigs_available")
        @Expose
        private Lbl_no_gigs_available lbl_no_gigs_available;
        @SerializedName("lbl_no_gigs_created")
        @Expose
        private Lbl_no_gigs_created lbl_no_gigs_created;
        @SerializedName("lbl_no_options")
        @Expose
        private Lbl_no_options lbl_no_options;
        @SerializedName("lbl_no_reviews_available")
        @Expose
        private Lbl_no_reviews_available lbl_no_reviews_available;
        @SerializedName("lbl_select_category")
        @Expose
        private Lbl_select_category lbl_select_category;
        @SerializedName("lbl_server_err")
        @Expose
        private Lbl_server_err lbl_server_err;
        @SerializedName("lbl_stripe")
        @Expose
        private Lbl_stripe lbl_stripe;
        @SerializedName("txt_choose_from_gallery")
        @Expose
        private Txt_choose_from_gallery txt_choose_from_gallery;
        @SerializedName("txt_take_photo")
        @Expose
        private Txt_take_photo txt_take_photo;

        @SerializedName("lbl_server_prblm")
        @Expose
        private Lbl_server_prblm lbl_server_prblm;

        @SerializedName("title_buy_services")
        @Expose
        private Title_buy_services title_buy_services;
        @SerializedName("title_chat")
        @Expose
        private Title_chat title_chat;
        @SerializedName("title_home")
        @Expose
        private Title_home title_home;
        @SerializedName("title_sell_services")
        @Expose
        private Title_sell_services title_sell_services;
        @SerializedName("title_buyer_request")
        @Expose
        private Title_buyer_request title_buyer_request;
        @SerializedName("title_seller_offer")
        @Expose
        private Title_seller_offer title_seller_offer;
        @SerializedName("title_settings")
        @Expose
        private Title_settings title_settings;

        @SerializedName("lbl_categories")
        @Expose
        private Lbl_categories lbl_categories;

        @SerializedName("lbl_select_any_one_field")
        @Expose
        private Lbl_select_any_one_field lbl_select_any_one_field;

        @SerializedName("lbl_fld_hi")
        @Expose
        private Lbl_fld_hi lbl_fld_hi;

        public Title_buyer_request getTitle_buyer_request() {
            return title_buyer_request;
        }

        public void setTitle_buyer_request(Title_buyer_request title_buyer_request) {
            this.title_buyer_request = title_buyer_request;
        }

        public Title_seller_offer getTitle_seller_offer() {
            return title_seller_offer;
        }

        public void setTitle_seller_offer(Title_seller_offer title_seller_offer) {
            this.title_seller_offer = title_seller_offer;
        }

        public Lbl_fld_hi getLbl_fld_hi() {
            return lbl_fld_hi;
        }

        public void setLbl_fld_hi(Lbl_fld_hi lbl_fld_hi) {
            this.lbl_fld_hi = lbl_fld_hi;
        }

        public Lbl_select_any_one_field getLbl_select_any_one_field() {
            return lbl_select_any_one_field;
        }

        public void setLbl_select_any_one_field(Lbl_select_any_one_field lbl_select_any_one_field) {
            this.lbl_select_any_one_field = lbl_select_any_one_field;
        }

        public Lbl_categories getLbl_categories() {
            return lbl_categories;
        }

        public void setLbl_categories(Lbl_categories lbl_categories) {
            this.lbl_categories = lbl_categories;
        }

        public Title_buy_services getTitle_buy_services() {
            return title_buy_services;
        }

        public void setTitle_buy_services(Title_buy_services title_buy_services) {
            this.title_buy_services = title_buy_services;
        }

        public Title_chat getTitle_chat() {
            return title_chat;
        }

        public void setTitle_chat(Title_chat title_chat) {
            this.title_chat = title_chat;
        }

        public Title_home getTitle_home() {
            return title_home;
        }

        public void setTitle_home(Title_home title_home) {
            this.title_home = title_home;
        }

        public Title_sell_services getTitle_sell_services() {
            return title_sell_services;
        }

        public void setTitle_sell_services(Title_sell_services title_sell_services) {
            this.title_sell_services = title_sell_services;
        }

        public Title_settings getTitle_settings() {
            return title_settings;
        }

        public void setTitle_settings(Title_settings title_settings) {
            this.title_settings = title_settings;
        }

        public Lbl_server_prblm getLbl_server_prblm() {
            return lbl_server_prblm;
        }

        public void setLbl_server_prblm(Lbl_server_prblm lbl_server_prblm) {
            this.lbl_server_prblm = lbl_server_prblm;
        }


        public Lbl_add_photo getLbl_add_photo() {
            return lbl_add_photo;
        }

        public void setLbl_add_photo(Lbl_add_photo lbl_add_photo) {
            this.lbl_add_photo = lbl_add_photo;
        }

        public Lbl_cancel getLbl_cancel() {
            return lbl_cancel;
        }

        public void setLbl_cancel(Lbl_cancel lbl_cancel) {
            this.lbl_cancel = lbl_cancel;
        }

        public Lbl_enable_internet getLbl_enable_internet() {
            return lbl_enable_internet;
        }

        public void setLbl_enable_internet(Lbl_enable_internet lbl_enable_internet) {
            this.lbl_enable_internet = lbl_enable_internet;
        }

        public Lbl_guest getLbl_guest() {
            return lbl_guest;
        }

        public void setLbl_guest(Lbl_guest lbl_guest) {
            this.lbl_guest = lbl_guest;
        }

        public Lbl_network_err getLbl_network_err() {
            return lbl_network_err;
        }

        public void setLbl_network_err(Lbl_network_err lbl_network_err) {
            this.lbl_network_err = lbl_network_err;
        }

        public Lbl_no_chat getLbl_no_chat() {
            return lbl_no_chat;
        }

        public void setLbl_no_chat(Lbl_no_chat lbl_no_chat) {
            this.lbl_no_chat = lbl_no_chat;
        }

        public Lbl_no_data_found getLbl_no_data_found() {
            return lbl_no_data_found;
        }

        public void setLbl_no_data_found(Lbl_no_data_found lbl_no_data_found) {
            this.lbl_no_data_found = lbl_no_data_found;
        }

        public Lbl_no_fav_available getLbl_no_fav_available() {
            return lbl_no_fav_available;
        }

        public void setLbl_no_fav_available(Lbl_no_fav_available lbl_no_fav_available) {
            this.lbl_no_fav_available = lbl_no_fav_available;
        }

        public Lbl_no_feedback_found getLbl_no_feedback_found() {
            return lbl_no_feedback_found;
        }

        public void setLbl_no_feedback_found(Lbl_no_feedback_found lbl_no_feedback_found) {
            this.lbl_no_feedback_found = lbl_no_feedback_found;
        }

        public Lbl_no_gigs_available getLbl_no_gigs_available() {
            return lbl_no_gigs_available;
        }

        public void setLbl_no_gigs_available(Lbl_no_gigs_available lbl_no_gigs_available) {
            this.lbl_no_gigs_available = lbl_no_gigs_available;
        }

        public Lbl_no_gigs_created getLbl_no_gigs_created() {
            return lbl_no_gigs_created;
        }

        public void setLbl_no_gigs_created(Lbl_no_gigs_created lbl_no_gigs_created) {
            this.lbl_no_gigs_created = lbl_no_gigs_created;
        }

        public Lbl_no_options getLbl_no_options() {
            return lbl_no_options;
        }

        public void setLbl_no_options(Lbl_no_options lbl_no_options) {
            this.lbl_no_options = lbl_no_options;
        }

        public Lbl_no_reviews_available getLbl_no_reviews_available() {
            return lbl_no_reviews_available;
        }

        public void setLbl_no_reviews_available(Lbl_no_reviews_available lbl_no_reviews_available) {
            this.lbl_no_reviews_available = lbl_no_reviews_available;
        }

        public Lbl_select_category getLbl_select_category() {
            return lbl_select_category;
        }

        public void setLbl_select_category(Lbl_select_category lbl_select_category) {
            this.lbl_select_category = lbl_select_category;
        }

        public Lbl_server_err getLbl_server_err() {
            return lbl_server_err;
        }

        public void setLbl_server_err(Lbl_server_err lbl_server_err) {
            this.lbl_server_err = lbl_server_err;
        }

        public Lbl_stripe getLbl_stripe() {
            return lbl_stripe;
        }

        public void setLbl_stripe(Lbl_stripe lbl_stripe) {
            this.lbl_stripe = lbl_stripe;
        }

        public Txt_choose_from_gallery getTxt_choose_from_gallery() {
            return txt_choose_from_gallery;
        }

        public void setTxt_choose_from_gallery(Txt_choose_from_gallery txt_choose_from_gallery) {
            this.txt_choose_from_gallery = txt_choose_from_gallery;
        }

        public Txt_take_photo getTxt_take_photo() {
            return txt_take_photo;
        }

        public void setTxt_take_photo(Txt_take_photo txt_take_photo) {
            this.txt_take_photo = txt_take_photo;
        }
    }

    public class Data {

        @SerializedName("language")
        @Expose
        private Language language;

        public Language getLanguage() {
            return language;
        }

        public void setLanguage(Language language) {
            this.language = language;
        }
    }

    public class Detail_gigs {

        @SerializedName("btn_contact")
        @Expose
        private Btn_contact btn_contact;
        @SerializedName("btn_order_now")
        @Expose
        private Btn_order_now btn_order_now;
        @SerializedName("btn_send")
        @Expose
        private Btn_send btn_send;
        @SerializedName("lbl_country")
        @Expose
        private Lbl_country lbl_country;
        @SerializedName("lbl_description")
        @Expose
        private Lbl_description lbl_description;
        @SerializedName("lbl_extras")
        @Expose
        private Lbl_extras lbl_extras;
        @SerializedName("lbl_more")
        @Expose
        private Lbl_more lbl_more;
        @SerializedName("lbl_recommended_gigs")
        @Expose
        private Lbl_recommended_gigs lbl_recommended_gigs;
        @SerializedName("lbl_reviews")
        @Expose
        private Lbl_reviews lbl_reviews;
        @SerializedName("lbl_similar_gigs")
        @Expose
        private Lbl_similar_gigs lbl_similar_gigs;
        @SerializedName("lbl_speaks")
        @Expose
        private Lbl_speaks lbl_speaks;
        @SerializedName("lbl_super_fast")
        @Expose
        private Lbl_super_fast lbl_super_fast;
        @SerializedName("lbl_total_views")
        @Expose
        private Lbl_total_views lbl_total_views;
        @SerializedName("lbl_user_count")
        @Expose
        private Lbl_user_count lbl_user_count;
        @SerializedName("lbl_user_info")
        @Expose
        private Lbl_user_info lbl_user_info;
        @SerializedName("lbl_your_msg")
        @Expose
        private Lbl_your_msg lbl_your_msg;
        @SerializedName("txt_fld_enter_msg")
        @Expose
        private Txt_fld_enter_msg txt_fld_enter_msg;
        @SerializedName("btn_edit_gigs")
        @Expose
        private Btn_edit_gigs btn_edit_gigs;

        public Btn_edit_gigs getBtn_edit_gigs() {
            return btn_edit_gigs;
        }

        public void setBtn_edit_gigs(Btn_edit_gigs btn_edit_gigs) {
            this.btn_edit_gigs = btn_edit_gigs;
        }

        public Btn_contact getBtn_contact() {
            return btn_contact;
        }

        public void setBtn_contact(Btn_contact btn_contact) {
            this.btn_contact = btn_contact;
        }

        public Btn_order_now getBtn_order_now() {
            return btn_order_now;
        }

        public void setBtn_order_now(Btn_order_now btn_order_now) {
            this.btn_order_now = btn_order_now;
        }

        public Btn_send getBtn_send() {
            return btn_send;
        }

        public void setBtn_send(Btn_send btn_send) {
            this.btn_send = btn_send;
        }

        public Lbl_country getLbl_country() {
            return lbl_country;
        }

        public void setLbl_country(Lbl_country lbl_country) {
            this.lbl_country = lbl_country;
        }

        public Lbl_description getLbl_description() {
            return lbl_description;
        }

        public void setLbl_description(Lbl_description lbl_description) {
            this.lbl_description = lbl_description;
        }

        public Lbl_extras getLbl_extras() {
            return lbl_extras;
        }

        public void setLbl_extras(Lbl_extras lbl_extras) {
            this.lbl_extras = lbl_extras;
        }

        public Lbl_more getLbl_more() {
            return lbl_more;
        }

        public void setLbl_more(Lbl_more lbl_more) {
            this.lbl_more = lbl_more;
        }

        public Lbl_recommended_gigs getLbl_recommended_gigs() {
            return lbl_recommended_gigs;
        }

        public void setLbl_recommended_gigs(Lbl_recommended_gigs lbl_recommended_gigs) {
            this.lbl_recommended_gigs = lbl_recommended_gigs;
        }

        public Lbl_reviews getLbl_reviews() {
            return lbl_reviews;
        }

        public void setLbl_reviews(Lbl_reviews lbl_reviews) {
            this.lbl_reviews = lbl_reviews;
        }

        public Lbl_similar_gigs getLbl_similar_gigs() {
            return lbl_similar_gigs;
        }

        public void setLbl_similar_gigs(Lbl_similar_gigs lbl_similar_gigs) {
            this.lbl_similar_gigs = lbl_similar_gigs;
        }

        public Lbl_speaks getLbl_speaks() {
            return lbl_speaks;
        }

        public void setLbl_speaks(Lbl_speaks lbl_speaks) {
            this.lbl_speaks = lbl_speaks;
        }

        public Lbl_super_fast getLbl_super_fast() {
            return lbl_super_fast;
        }

        public void setLbl_super_fast(Lbl_super_fast lbl_super_fast) {
            this.lbl_super_fast = lbl_super_fast;
        }

        public Lbl_total_views getLbl_total_views() {
            return lbl_total_views;
        }

        public void setLbl_total_views(Lbl_total_views lbl_total_views) {
            this.lbl_total_views = lbl_total_views;
        }

        public Lbl_user_count getLbl_user_count() {
            return lbl_user_count;
        }

        public void setLbl_user_count(Lbl_user_count lbl_user_count) {
            this.lbl_user_count = lbl_user_count;
        }

        public Lbl_user_info getLbl_user_info() {
            return lbl_user_info;
        }

        public void setLbl_user_info(Lbl_user_info lbl_user_info) {
            this.lbl_user_info = lbl_user_info;
        }

        public Lbl_your_msg getLbl_your_msg() {
            return lbl_your_msg;
        }

        public void setLbl_your_msg(Lbl_your_msg lbl_your_msg) {
            this.lbl_your_msg = lbl_your_msg;
        }

        public Txt_fld_enter_msg getTxt_fld_enter_msg() {
            return txt_fld_enter_msg;
        }

        public void setTxt_fld_enter_msg(Txt_fld_enter_msg txt_fld_enter_msg) {
            this.txt_fld_enter_msg = txt_fld_enter_msg;
        }

    }


    public class Forgot_password_screen {


        @SerializedName("lbl_forgot_password")
        @Expose
        private Lbl_forgot_password lbl_forgot_password;

        @SerializedName("lbl_fpwd_info")
        @Expose
        private Lbl_fpwd_info lbl_fpwd_info;
        @SerializedName("lbl_login")
        @Expose
        private Lbl_login lbl_login;
        @SerializedName("txt_fld_email")
        @Expose
        private Txt_fld_email txt_fld_email;

        public Lbl_forgot_password getLbl_forgot_password() {
            return lbl_forgot_password;
        }

        public void setLbl_forgot_password(Lbl_forgot_password lbl_forgot_password) {
            this.lbl_forgot_password = lbl_forgot_password;
        }

        public Lbl_fpwd_info getLbl_fpwd_info() {
            return lbl_fpwd_info;
        }

        public void setLbl_fpwd_info(Lbl_fpwd_info lbl_fpwd_info) {
            this.lbl_fpwd_info = lbl_fpwd_info;
        }

        public Lbl_login getLbl_login() {
            return lbl_login;
        }

        public void setLbl_login(Lbl_login lbl_login) {
            this.lbl_login = lbl_login;
        }

        public Txt_fld_email getTxt_fld_email() {
            return txt_fld_email;
        }

        public void setTxt_fld_email(Txt_fld_email txt_fld_email) {
            this.txt_fld_email = txt_fld_email;
        }

    }


    public class Home_screen {

        @SerializedName("lbl_latest_gigs")
        @Expose
        private Lbl_latest_gigs lbl_latest_gigs;
        @SerializedName("lbl_popular_gigs")
        @Expose
        private Lbl_popular_gigs lbl_popular_gigs;
        @SerializedName("lbl_recent_gigs")
        @Expose
        private Lbl_recent_gigs lbl_recent_gigs;
        @SerializedName("lbl_top_category")
        @Expose
        private Lbl_top_category lbl_top_category;
        @SerializedName("lbl_view_all")
        @Expose
        private Lbl_view_all lbl_view_all;

        @SerializedName("txt_fld_search")
        @Expose
        private Txt_fld_search txt_fld_search;

        public Txt_fld_search getTxt_fld_search() {
            return txt_fld_search;
        }

        public void setTxt_fld_search(Txt_fld_search txt_fld_search) {
            this.txt_fld_search = txt_fld_search;
        }

        public Lbl_latest_gigs getLbl_latest_gigs() {
            return lbl_latest_gigs;
        }

        public void setLbl_latest_gigs(Lbl_latest_gigs lbl_latest_gigs) {
            this.lbl_latest_gigs = lbl_latest_gigs;
        }

        public Lbl_popular_gigs getLbl_popular_gigs() {
            return lbl_popular_gigs;
        }

        public void setLbl_popular_gigs(Lbl_popular_gigs lbl_popular_gigs) {
            this.lbl_popular_gigs = lbl_popular_gigs;
        }

        public Lbl_recent_gigs getLbl_recent_gigs() {
            return lbl_recent_gigs;
        }

        public void setLbl_recent_gigs(Lbl_recent_gigs lbl_recent_gigs) {
            this.lbl_recent_gigs = lbl_recent_gigs;
        }

        public Lbl_top_category getLbl_top_category() {
            return lbl_top_category;
        }

        public void setLbl_top_category(Lbl_top_category lbl_top_category) {
            this.lbl_top_category = lbl_top_category;
        }

        public Lbl_view_all getLbl_view_all() {
            return lbl_view_all;
        }

        public void setLbl_view_all(Lbl_view_all lbl_view_all) {
            this.lbl_view_all = lbl_view_all;
        }

    }


    public class Intro_screen {

        @SerializedName("lbl_destination")
        @Expose
        private Lbl_destination lbl_destination;
        @SerializedName("lbl_earn_cash")
        @Expose
        private Lbl_earn_cash lbl_earn_cash;
        @SerializedName("lbl_get_rewarded")
        @Expose
        private Lbl_get_rewarded lbl_get_rewarded;
        @SerializedName("lbl_got_it")
        @Expose
        private Lbl_got_it lbl_got_it;
        @SerializedName("lbl_next")
        @Expose
        private Lbl_next lbl_next;
        @SerializedName("lbl_showcase")
        @Expose
        private Lbl_showcase lbl_showcase;
        @SerializedName("lbl_skip")
        @Expose
        private Lbl_skip lbl_skip;

        public Lbl_destination getLbl_destination() {
            return lbl_destination;
        }

        public void setLbl_destination(Lbl_destination lbl_destination) {
            this.lbl_destination = lbl_destination;
        }

        public Lbl_earn_cash getLbl_earn_cash() {
            return lbl_earn_cash;
        }

        public void setLbl_earn_cash(Lbl_earn_cash lbl_earn_cash) {
            this.lbl_earn_cash = lbl_earn_cash;
        }

        public Lbl_get_rewarded getLbl_get_rewarded() {
            return lbl_get_rewarded;
        }

        public void setLbl_get_rewarded(Lbl_get_rewarded lbl_get_rewarded) {
            this.lbl_get_rewarded = lbl_get_rewarded;
        }

        public Lbl_got_it getLbl_got_it() {
            return lbl_got_it;
        }

        public void setLbl_got_it(Lbl_got_it lbl_got_it) {
            this.lbl_got_it = lbl_got_it;
        }

        public Lbl_next getLbl_next() {
            return lbl_next;
        }

        public void setLbl_next(Lbl_next lbl_next) {
            this.lbl_next = lbl_next;
        }

        public Lbl_showcase getLbl_showcase() {
            return lbl_showcase;
        }

        public void setLbl_showcase(Lbl_showcase lbl_showcase) {
            this.lbl_showcase = lbl_showcase;
        }

        public Lbl_skip getLbl_skip() {
            return lbl_skip;
        }

        public void setLbl_skip(Lbl_skip lbl_skip) {
            this.lbl_skip = lbl_skip;
        }

    }


    public class Btn_accept {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Btn_edit_gigs {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Btn_contact {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Btn_create_gig {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Btn_order_now {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Btn_register {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Btn_save {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Btn_send {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Btn_submit {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Btn_submit_ {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Btn_terms_and_conditions {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Btn_update {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Change_password_screen {

        @SerializedName("btn_submit")
        @Expose
        private Btn_submit btn_submit;
        @SerializedName("lbl_change_password")
        @Expose
        private Lbl_change_password lbl_change_password;
        @SerializedName("lbl_current_pwd")
        @Expose
        private Lbl_current_pwd lbl_current_pwd;
        @SerializedName("txt_fld_confirm_pwd")
        @Expose
        private Txt_fld_confirm_pwd txt_fld_confirm_pwd;
        @SerializedName("txt_fld_new_pwd")
        @Expose
        private Txt_fld_new_pwd txt_fld_new_pwd;

        @SerializedName("validate_both_pwd")
        @Expose
        private Validate_both_pwd validate_both_pwd;

        public Validate_both_pwd getValidate_both_pwd() {
            return validate_both_pwd;
        }

        public void setValidate_both_pwd(Validate_both_pwd validate_both_pwd) {
            this.validate_both_pwd = validate_both_pwd;
        }

        public Btn_submit getBtn_submit() {
            return btn_submit;
        }

        public void setBtn_submit(Btn_submit btn_submit) {
            this.btn_submit = btn_submit;
        }

        public Lbl_change_password getLbl_change_password() {
            return lbl_change_password;
        }

        public void setLbl_change_password(Lbl_change_password lbl_change_password) {
            this.lbl_change_password = lbl_change_password;
        }

        public Lbl_current_pwd getLbl_current_pwd() {
            return lbl_current_pwd;
        }

        public void setLbl_current_pwd(Lbl_current_pwd lbl_current_pwd) {
            this.lbl_current_pwd = lbl_current_pwd;
        }

        public Txt_fld_confirm_pwd getTxt_fld_confirm_pwd() {
            return txt_fld_confirm_pwd;
        }

        public void setTxt_fld_confirm_pwd(Txt_fld_confirm_pwd txt_fld_confirm_pwd) {
            this.txt_fld_confirm_pwd = txt_fld_confirm_pwd;
        }

        public Txt_fld_new_pwd getTxt_fld_new_pwd() {
            return txt_fld_new_pwd;
        }

        public void setTxt_fld_new_pwd(Txt_fld_new_pwd txt_fld_new_pwd) {
            this.txt_fld_new_pwd = txt_fld_new_pwd;
        }

    }


    public class Language {

        @SerializedName("change_password_screen")
        @Expose
        private Change_password_screen change_password_screen;
        @SerializedName("common_js")
        @Expose
        private Common_js common_js;
        @SerializedName("common_strings")
        @Expose
        private Common_strings common_strings;
        @SerializedName("detail_gigs")
        @Expose
        private Detail_gigs detail_gigs;
        @SerializedName("forgot_password_screen")
        @Expose
        private Forgot_password_screen forgot_password_screen;
        @SerializedName("home_screen")
        @Expose
        private Home_screen home_screen;
        @SerializedName("intro_screen")
        @Expose
        private Intro_screen intro_screen;
        @SerializedName("login_screen")
        @Expose
        private Login_screen login_screen;
        @SerializedName("my_activity_screen")
        @Expose
        private My_activity_screen my_activity_screen;
        @SerializedName("navigation_screen")
        @Expose
        private Navigation_screen navigation_screen;
        @SerializedName("register_screen")
        @Expose
        private Register_screen register_screen;
        @SerializedName("search_gigs_screen")
        @Expose
        private Search_gigs_screen search_gigs_screen;
        @SerializedName("sell_gigs_screen")
        @Expose
        private Sell_gigs_screen sell_gigs_screen;
        @SerializedName("settings_screen")
        @Expose
        private Settings_screen settings_screen;
        @SerializedName("stripe_payment_screen")
        @Expose
        private Stripe_payment_screen stripe_payment_screen;
        @SerializedName("tabbar_screen")
        @Expose
        private Tabbar_screen tabbar_screen;

        @SerializedName("cart_screen")
        @Expose
        private Cart_screen cart_screen;

        public Cart_screen getCart_screen() {
            return cart_screen;
        }

        public void setCart_screen(Cart_screen cart_screen) {
            this.cart_screen = cart_screen;
        }

        public Change_password_screen getChange_password_screen() {
            return change_password_screen;
        }

        public void setChange_password_screen(Change_password_screen change_password_screen) {
            this.change_password_screen = change_password_screen;
        }

        public Common_js getCommon_js() {
            return common_js;
        }

        public void setCommon_js(Common_js common_js) {
            this.common_js = common_js;
        }

        public Common_strings getCommon_strings() {
            return common_strings;
        }

        public void setCommon_strings(Common_strings common_strings) {
            this.common_strings = common_strings;
        }

        public Detail_gigs getDetail_gigs() {
            return detail_gigs;
        }

        public void setDetail_gigs(Detail_gigs detail_gigs) {
            this.detail_gigs = detail_gigs;
        }

        public Forgot_password_screen getForgot_password_screen() {
            return forgot_password_screen;
        }

        public void setForgot_password_screen(Forgot_password_screen forgot_password_screen) {
            this.forgot_password_screen = forgot_password_screen;
        }

        public Home_screen getHome_screen() {
            return home_screen;
        }

        public void setHome_screen(Home_screen home_screen) {
            this.home_screen = home_screen;
        }

        public Intro_screen getIntro_screen() {
            return intro_screen;
        }

        public void setIntro_screen(Intro_screen intro_screen) {
            this.intro_screen = intro_screen;
        }

        public Login_screen getLogin_screen() {
            return login_screen;
        }

        public void setLogin_screen(Login_screen login_screen) {
            this.login_screen = login_screen;
        }

        public My_activity_screen getMy_activity_screen() {
            return my_activity_screen;
        }

        public void setMy_activity_screen(My_activity_screen my_activity_screen) {
            this.my_activity_screen = my_activity_screen;
        }

        public Navigation_screen getNavigation_screen() {
            return navigation_screen;
        }

        public void setNavigation_screen(Navigation_screen navigation_screen) {
            this.navigation_screen = navigation_screen;
        }

        public Register_screen getRegister_screen() {
            return register_screen;
        }

        public void setRegister_screen(Register_screen register_screen) {
            this.register_screen = register_screen;
        }

        public Search_gigs_screen getSearch_gigs_screen() {
            return search_gigs_screen;
        }

        public void setSearch_gigs_screen(Search_gigs_screen search_gigs_screen) {
            this.search_gigs_screen = search_gigs_screen;
        }

        public Sell_gigs_screen getSell_gigs_screen() {
            return sell_gigs_screen;
        }

        public void setSell_gigs_screen(Sell_gigs_screen sell_gigs_screen) {
            this.sell_gigs_screen = sell_gigs_screen;
        }

        public Settings_screen getSettings_screen() {
            return settings_screen;
        }

        public void setSettings_screen(Settings_screen settings_screen) {
            this.settings_screen = settings_screen;
        }

        public Stripe_payment_screen getStripe_payment_screen() {
            return stripe_payment_screen;
        }

        public void setStripe_payment_screen(Stripe_payment_screen stripe_payment_screen) {
            this.stripe_payment_screen = stripe_payment_screen;
        }

        public Tabbar_screen getTabbar_screen() {
            return tabbar_screen;
        }

        public void setTabbar_screen(Tabbar_screen tabbar_screen) {
            this.tabbar_screen = tabbar_screen;
        }

    }


    public class Lbl_Ican {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_activity {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_add_more_items {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_add_photo {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_already_mem {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_arabic {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_buy {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_buyser_name {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_cancel {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_change_language {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_change_password {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_change_pwd {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Cart_screen {

        @SerializedName("lbl_item")
        @Expose
        private Lbl_item lbl_item;
        @SerializedName("lbl_product_name")
        @Expose
        private Lbl_product_name lbl_product_name;
        @SerializedName("lbl_quantity")
        @Expose
        private Lbl_quantity lbl_quantity;
        @SerializedName("lbl_total")
        @Expose
        private Lbl_total lbl_total;
        @SerializedName("lbl_total_amount")
        @Expose
        private Lbl_total_amount lbl_total_amount;
        @SerializedName("lbl_select_payment_method")
        @Expose
        private Lbl_select_payment_method lbl_select_payment_method;
        @SerializedName("lbl_cart_bottom_content")
        @Expose
        private Lbl_cart_bottom_content lbl_cart_bottom_content;
        @SerializedName("lbl_header_title")
        @Expose
        private Lbl_header_title lbl_header_title;
        @SerializedName("btn_buy_now")
        @Expose
        private Btn_buy_now btn_buy_now;
        @SerializedName("lbl_choose_payment")
        @Expose
        private Lbl_choose_payment lbl_choose_payment;

        public Lbl_item getLbl_item() {
            return lbl_item;
        }

        public void setLbl_item(Lbl_item lbl_item) {
            this.lbl_item = lbl_item;
        }

        public Lbl_product_name getLbl_product_name() {
            return lbl_product_name;
        }

        public void setLbl_product_name(Lbl_product_name lbl_product_name) {
            this.lbl_product_name = lbl_product_name;
        }

        public Lbl_quantity getLbl_quantity() {
            return lbl_quantity;
        }

        public void setLbl_quantity(Lbl_quantity lbl_quantity) {
            this.lbl_quantity = lbl_quantity;
        }

        public Lbl_total getLbl_total() {
            return lbl_total;
        }

        public void setLbl_total(Lbl_total lbl_total) {
            this.lbl_total = lbl_total;
        }

        public Lbl_total_amount getLbl_total_amount() {
            return lbl_total_amount;
        }

        public void setLbl_total_amount(Lbl_total_amount lbl_total_amount) {
            this.lbl_total_amount = lbl_total_amount;
        }

        public Lbl_select_payment_method getLbl_select_payment_method() {
            return lbl_select_payment_method;
        }

        public void setLbl_select_payment_method(Lbl_select_payment_method lbl_select_payment_method) {
            this.lbl_select_payment_method = lbl_select_payment_method;
        }

        public Lbl_cart_bottom_content getLbl_cart_bottom_content() {
            return lbl_cart_bottom_content;
        }

        public void setLbl_cart_bottom_content(Lbl_cart_bottom_content lbl_cart_bottom_content) {
            this.lbl_cart_bottom_content = lbl_cart_bottom_content;
        }

        public Lbl_header_title getLbl_header_title() {
            return lbl_header_title;
        }

        public void setLbl_header_title(Lbl_header_title lbl_header_title) {
            this.lbl_header_title = lbl_header_title;
        }

        public Btn_buy_now getBtn_buy_now() {
            return btn_buy_now;
        }

        public void setBtn_buy_now(Btn_buy_now btn_buy_now) {
            this.btn_buy_now = btn_buy_now;
        }

        public Lbl_choose_payment getLbl_choose_payment() {
            return lbl_choose_payment;
        }

        public void setLbl_choose_payment(Lbl_choose_payment lbl_choose_payment) {
            this.lbl_choose_payment = lbl_choose_payment;
        }

    }

    public class Lbl_chat {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_choose_lang {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_completed {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_country {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_country_ {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_current_balance {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_current_pwd {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_day {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_declined {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_delivery_date {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_description {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_destination {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_earn_cash {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_earn_money {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_edit_profile {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_enable_internet {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_english {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_extras {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_facebook {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_favourites {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_feedback {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_forgot_pwd {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_fpwd_info {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_get_rewarded {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_google {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_got_it {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_guest {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_help_and_support {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_home {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_last_visited_gigs {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_latest_gigs {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_leave_feedback {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_login {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_login_ {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_logout {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_more {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_my_activity {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_my_gigs {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_network_err {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_next {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_no {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_no_chat {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_no_data_found {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_no_fav_available {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_no_feedback_found {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_no_gigs_available {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_no_gigs_created {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_no_options {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_no_reviews_available {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_not_a_member {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_onsite {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_order_cancel {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_order_id {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_order_status {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_payment {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_popular_gigs {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_purchases {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_rate_feedback {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_rating {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_recent_gigs {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_recommended_gigs {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_register {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_register_now {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_remote {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_request_sent {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_reviews {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_sales {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_search_gigs {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_search_gigs_ {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_select_category {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_select_category_ {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_select_category__ {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_select_category___ {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_select_country {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_select_lang {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_select_state {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_sell {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_seller_name {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_send_feedback {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_server_err {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_settings {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_offer {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_request {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_settings_ {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_showcase {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_similar_gigs {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_skip {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_social_login {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_speaks {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_state {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_stripe {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_sub_category {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_super_fast {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_super_fast_delivery {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_terms_and_conditions {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_terms_condition {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_thanks_rating {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_top_category {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_total_views {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_user_count {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_user_info {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_view_all {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_wallet {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_work_option {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_yes {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lbl_your_msg {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Lg_error_occured {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Login_screen {

        @SerializedName("lbl_facebook")
        @Expose
        private Lbl_facebook lbl_facebook;
        @SerializedName("lbl_forgot_pwd")
        @Expose
        private Lbl_forgot_pwd lbl_forgot_pwd;
        @SerializedName("lbl_google")
        @Expose
        private Lbl_google lbl_google;
        @SerializedName("lbl_not_a_member")
        @Expose
        private Lbl_not_a_member lbl_not_a_member;
        @SerializedName("lbl_register_now")
        @Expose
        private Lbl_register_now lbl_register_now;
        @SerializedName("lbl_social_login")
        @Expose
        private Lbl_social_login lbl_social_login;
        @SerializedName("txt_fld_login")
        @Expose
        private Txt_fld_login txt_fld_login;
        @SerializedName("txt_fld_pwd")
        @Expose
        private Txt_fld_pwd txt_fld_pwd;
        @SerializedName("txt_fld_username")
        @Expose
        private Txt_fld_username txt_fld_username;

        public Lbl_facebook getLbl_facebook() {
            return lbl_facebook;
        }

        public void setLbl_facebook(Lbl_facebook lbl_facebook) {
            this.lbl_facebook = lbl_facebook;
        }

        public Lbl_forgot_pwd getLbl_forgot_pwd() {
            return lbl_forgot_pwd;
        }

        public void setLbl_forgot_pwd(Lbl_forgot_pwd lbl_forgot_pwd) {
            this.lbl_forgot_pwd = lbl_forgot_pwd;
        }

        public Lbl_google getLbl_google() {
            return lbl_google;
        }

        public void setLbl_google(Lbl_google lbl_google) {
            this.lbl_google = lbl_google;
        }

        public Lbl_not_a_member getLbl_not_a_member() {
            return lbl_not_a_member;
        }

        public void setLbl_not_a_member(Lbl_not_a_member lbl_not_a_member) {
            this.lbl_not_a_member = lbl_not_a_member;
        }

        public Lbl_register_now getLbl_register_now() {
            return lbl_register_now;
        }

        public void setLbl_register_now(Lbl_register_now lbl_register_now) {
            this.lbl_register_now = lbl_register_now;
        }

        public Lbl_social_login getLbl_social_login() {
            return lbl_social_login;
        }

        public void setLbl_social_login(Lbl_social_login lbl_social_login) {
            this.lbl_social_login = lbl_social_login;
        }

        public Txt_fld_login getTxt_fld_login() {
            return txt_fld_login;
        }

        public void setTxt_fld_login(Txt_fld_login txt_fld_login) {
            this.txt_fld_login = txt_fld_login;
        }

        public Txt_fld_pwd getTxt_fld_pwd() {
            return txt_fld_pwd;
        }

        public void setTxt_fld_pwd(Txt_fld_pwd txt_fld_pwd) {
            this.txt_fld_pwd = txt_fld_pwd;
        }

        public Txt_fld_username getTxt_fld_username() {
            return txt_fld_username;
        }

        public void setTxt_fld_username(Txt_fld_username txt_fld_username) {
            this.txt_fld_username = txt_fld_username;
        }

    }


    public class My_activity_screen {

        @SerializedName("btn_accept")
        @Expose
        private Btn_accept btn_accept;
        @SerializedName("lbl_activity")
        @Expose
        private Lbl_activity lbl_activity;
        @SerializedName("lbl_buyser_name")
        @Expose
        private Lbl_buyser_name lbl_buyser_name;
        @SerializedName("lbl_completed")
        @Expose
        private Lbl_completed lbl_completed;
        @SerializedName("lbl_current_balance")
        @Expose
        private Lbl_current_balance lbl_current_balance;
        @SerializedName("lbl_declined")
        @Expose
        private Lbl_declined lbl_declined;
        @SerializedName("lbl_delivery_date")
        @Expose
        private Lbl_delivery_date lbl_delivery_date;
        @SerializedName("lbl_feedback")
        @Expose
        private Lbl_feedback lbl_feedback;
        @SerializedName("lbl_leave_feedback")
        @Expose
        private Lbl_leave_feedback lbl_leave_feedback;
        @SerializedName("lbl_order_cancel")
        @Expose
        private Lbl_order_cancel lbl_order_cancel;
        @SerializedName("lbl_order_id")
        @Expose
        private Lbl_order_id lbl_order_id;
        @SerializedName("lbl_order_status")
        @Expose
        private Lbl_order_status lbl_order_status;
        @SerializedName("lbl_payment")
        @Expose
        private Lbl_payment lbl_payment;
        @SerializedName("lbl_purchases")
        @Expose
        private Lbl_purchases lbl_purchases;
        @SerializedName("lbl_rate_feedback")
        @Expose
        private Lbl_rate_feedback lbl_rate_feedback;
        @SerializedName("lbl_rating")
        @Expose
        private Lbl_rating lbl_rating;
        @SerializedName("lbl_request_sent")
        @Expose
        private Lbl_request_sent lbl_request_sent;
        @SerializedName("lbl_sales")
        @Expose
        private Lbl_sales lbl_sales;
        @SerializedName("lbl_seller_name")
        @Expose
        private Lbl_seller_name lbl_seller_name;
        @SerializedName("lbl_send_feedback")
        @Expose
        private Lbl_send_feedback lbl_send_feedback;
        @SerializedName("lbl_thanks_rating")
        @Expose
        private Lbl_thanks_rating lbl_thanks_rating;
        @SerializedName("txt_fld_cancel_order")
        @Expose
        private Txt_fld_cancel_order txt_fld_cancel_order;
        @SerializedName("txt_fld_comments")
        @Expose
        private Txt_fld_comments txt_fld_comments;
        @SerializedName("txt_fld_update")
        @Expose
        private Txt_fld_update txt_fld_update;

        @SerializedName("err_enter_reason")
        @Expose
        private Err_enter_reason err_enter_reason;
        @SerializedName("err_enter_stripe_details")
        @Expose
        private Err_enter_stripe_details err_enter_stripe_details;

        @SerializedName("err_add_stripe")
        @Expose
        private Err_add_stripe err_add_stripe;

        @SerializedName("lbl_see_feedback")
        @Expose
        private Lbl_see_feedback lbl_see_feedback;
        @SerializedName("lbl_comments")
        @Expose
        private Lbl_comments lbl_comments;

        @SerializedName("lbl_fld_declined")
        @Expose
        private Lbl_fld_declined lbl_fld_declined;
        @SerializedName("lbl_fld_pending")
        @Expose
        private Lbl_fld_pending lbl_fld_pending;
        @SerializedName("lbl_fld_processing")
        @Expose
        private Lbl_fld_processing lbl_fld_processing;
        @SerializedName("lbl_fld_completed")
        @Expose
        private Lbl_fld_completed lbl_fld_completed;

        @SerializedName("lbl_fld_select_status")
        @Expose
        private Lbl_fld_select_status lbl_fld_select_status;

        @SerializedName("lbl_activate_order")
        @Expose
        private Lbl_activate_order lbl_activate_order;
        @SerializedName("lbl_reject_order")
        @Expose
        private Lbl_reject_order lbl_reject_order;


        @SerializedName("lbl_fld_reject_request")
        @Expose
        private Lbl_fld_reject_request lbl_fld_reject_request;

        public Lbl_fld_reject_request getLbl_fld_reject_request() {
            return lbl_fld_reject_request;
        }

        public void setLbl_fld_reject_request(Lbl_fld_reject_request lbl_fld_reject_request) {
            this.lbl_fld_reject_request = lbl_fld_reject_request;
        }

        public Lbl_activate_order getLbl_activate_order() {
            return lbl_activate_order;
        }

        public void setLbl_activate_order(Lbl_activate_order lbl_activate_order) {
            this.lbl_activate_order = lbl_activate_order;
        }

        public Lbl_reject_order getLbl_reject_order() {
            return lbl_reject_order;
        }

        public void setLbl_reject_order(Lbl_reject_order lbl_reject_order) {
            this.lbl_reject_order = lbl_reject_order;
        }

        public Lbl_fld_select_status getLbl_fld_select_status() {
            return lbl_fld_select_status;
        }

        public void setLbl_fld_select_status(Lbl_fld_select_status lbl_fld_select_status) {
            this.lbl_fld_select_status = lbl_fld_select_status;
        }

        public Lbl_fld_declined getLbl_fld_declined() {
            return lbl_fld_declined;
        }

        public void setLbl_fld_declined(Lbl_fld_declined lbl_fld_declined) {
            this.lbl_fld_declined = lbl_fld_declined;
        }

        public Lbl_fld_pending getLbl_fld_pending() {
            return lbl_fld_pending;
        }

        public void setLbl_fld_pending(Lbl_fld_pending lbl_fld_pending) {
            this.lbl_fld_pending = lbl_fld_pending;
        }

        public Lbl_fld_processing getLbl_fld_processing() {
            return lbl_fld_processing;
        }

        public void setLbl_fld_processing(Lbl_fld_processing lbl_fld_processing) {
            this.lbl_fld_processing = lbl_fld_processing;
        }

        public Lbl_fld_completed getLbl_fld_completed() {
            return lbl_fld_completed;
        }

        public void setLbl_fld_completed(Lbl_fld_completed lbl_fld_completed) {
            this.lbl_fld_completed = lbl_fld_completed;
        }

        public Lbl_see_feedback getLbl_see_feedback() {
            return lbl_see_feedback;
        }

        public void setLbl_see_feedback(Lbl_see_feedback lbl_see_feedback) {
            this.lbl_see_feedback = lbl_see_feedback;
        }

        public Lbl_comments getLbl_comments() {
            return lbl_comments;
        }

        public void setLbl_comments(Lbl_comments lbl_comments) {
            this.lbl_comments = lbl_comments;
        }

        public Err_add_stripe getErr_add_stripe() {
            return err_add_stripe;
        }

        public void setErr_add_stripe(Err_add_stripe err_add_stripe) {
            this.err_add_stripe = err_add_stripe;
        }


        public Err_enter_reason getErr_enter_reason() {
            return err_enter_reason;
        }

        public void setErr_enter_reason(Err_enter_reason err_enter_reason) {
            this.err_enter_reason = err_enter_reason;
        }

        public Err_enter_stripe_details getErr_enter_stripe_details() {
            return err_enter_stripe_details;
        }

        public void setErr_enter_stripe_details(Err_enter_stripe_details err_enter_stripe_details) {
            this.err_enter_stripe_details = err_enter_stripe_details;
        }

        public Btn_accept getBtn_accept() {
            return btn_accept;
        }

        public void setBtn_accept(Btn_accept btn_accept) {
            this.btn_accept = btn_accept;
        }

        public Lbl_activity getLbl_activity() {
            return lbl_activity;
        }

        public void setLbl_activity(Lbl_activity lbl_activity) {
            this.lbl_activity = lbl_activity;
        }

        public Lbl_buyser_name getLbl_buyser_name() {
            return lbl_buyser_name;
        }

        public void setLbl_buyser_name(Lbl_buyser_name lbl_buyser_name) {
            this.lbl_buyser_name = lbl_buyser_name;
        }

        public Lbl_completed getLbl_completed() {
            return lbl_completed;
        }

        public void setLbl_completed(Lbl_completed lbl_completed) {
            this.lbl_completed = lbl_completed;
        }

        public Lbl_current_balance getLbl_current_balance() {
            return lbl_current_balance;
        }

        public void setLbl_current_balance(Lbl_current_balance lbl_current_balance) {
            this.lbl_current_balance = lbl_current_balance;
        }

        public Lbl_declined getLbl_declined() {
            return lbl_declined;
        }

        public void setLbl_declined(Lbl_declined lbl_declined) {
            this.lbl_declined = lbl_declined;
        }

        public Lbl_delivery_date getLbl_delivery_date() {
            return lbl_delivery_date;
        }

        public void setLbl_delivery_date(Lbl_delivery_date lbl_delivery_date) {
            this.lbl_delivery_date = lbl_delivery_date;
        }

        public Lbl_feedback getLbl_feedback() {
            return lbl_feedback;
        }

        public void setLbl_feedback(Lbl_feedback lbl_feedback) {
            this.lbl_feedback = lbl_feedback;
        }

        public Lbl_leave_feedback getLbl_leave_feedback() {
            return lbl_leave_feedback;
        }

        public void setLbl_leave_feedback(Lbl_leave_feedback lbl_leave_feedback) {
            this.lbl_leave_feedback = lbl_leave_feedback;
        }

        public Lbl_order_cancel getLbl_order_cancel() {
            return lbl_order_cancel;
        }

        public void setLbl_order_cancel(Lbl_order_cancel lbl_order_cancel) {
            this.lbl_order_cancel = lbl_order_cancel;
        }

        public Lbl_order_id getLbl_order_id() {
            return lbl_order_id;
        }

        public void setLbl_order_id(Lbl_order_id lbl_order_id) {
            this.lbl_order_id = lbl_order_id;
        }

        public Lbl_order_status getLbl_order_status() {
            return lbl_order_status;
        }

        public void setLbl_order_status(Lbl_order_status lbl_order_status) {
            this.lbl_order_status = lbl_order_status;
        }

        public Lbl_payment getLbl_payment() {
            return lbl_payment;
        }

        public void setLbl_payment(Lbl_payment lbl_payment) {
            this.lbl_payment = lbl_payment;
        }

        public Lbl_purchases getLbl_purchases() {
            return lbl_purchases;
        }

        public void setLbl_purchases(Lbl_purchases lbl_purchases) {
            this.lbl_purchases = lbl_purchases;
        }

        public Lbl_rate_feedback getLbl_rate_feedback() {
            return lbl_rate_feedback;
        }

        public void setLbl_rate_feedback(Lbl_rate_feedback lbl_rate_feedback) {
            this.lbl_rate_feedback = lbl_rate_feedback;
        }

        public Lbl_rating getLbl_rating() {
            return lbl_rating;
        }

        public void setLbl_rating(Lbl_rating lbl_rating) {
            this.lbl_rating = lbl_rating;
        }

        public Lbl_request_sent getLbl_request_sent() {
            return lbl_request_sent;
        }

        public void setLbl_request_sent(Lbl_request_sent lbl_request_sent) {
            this.lbl_request_sent = lbl_request_sent;
        }

        public Lbl_sales getLbl_sales() {
            return lbl_sales;
        }

        public void setLbl_sales(Lbl_sales lbl_sales) {
            this.lbl_sales = lbl_sales;
        }

        public Lbl_seller_name getLbl_seller_name() {
            return lbl_seller_name;
        }

        public void setLbl_seller_name(Lbl_seller_name lbl_seller_name) {
            this.lbl_seller_name = lbl_seller_name;
        }

        public Lbl_send_feedback getLbl_send_feedback() {
            return lbl_send_feedback;
        }

        public void setLbl_send_feedback(Lbl_send_feedback lbl_send_feedback) {
            this.lbl_send_feedback = lbl_send_feedback;
        }

        public Lbl_thanks_rating getLbl_thanks_rating() {
            return lbl_thanks_rating;
        }

        public void setLbl_thanks_rating(Lbl_thanks_rating lbl_thanks_rating) {
            this.lbl_thanks_rating = lbl_thanks_rating;
        }

        public Txt_fld_cancel_order getTxt_fld_cancel_order() {
            return txt_fld_cancel_order;
        }

        public void setTxt_fld_cancel_order(Txt_fld_cancel_order txt_fld_cancel_order) {
            this.txt_fld_cancel_order = txt_fld_cancel_order;
        }

        public Txt_fld_comments getTxt_fld_comments() {
            return txt_fld_comments;
        }

        public void setTxt_fld_comments(Txt_fld_comments txt_fld_comments) {
            this.txt_fld_comments = txt_fld_comments;
        }

        public Txt_fld_update getTxt_fld_update() {
            return txt_fld_update;
        }

        public void setTxt_fld_update(Txt_fld_update txt_fld_update) {
            this.txt_fld_update = txt_fld_update;
        }

    }


    public class Navigation_screen {

        @SerializedName("lbl_favourites")
        @Expose
        private Lbl_favourites lbl_favourites;
        @SerializedName("lbl_last_visited_gigs")
        @Expose
        private Lbl_last_visited_gigs lbl_last_visited_gigs;
        @SerializedName("lbl_my_activity")
        @Expose
        private Lbl_my_activity lbl_my_activity;
        @SerializedName("lbl_my_gigs")
        @Expose
        private Lbl_my_gigs lbl_my_gigs;
        @SerializedName("lbl_search_gigs")
        @Expose
        private Lbl_search_gigs lbl_search_gigs;

        public Lbl_favourites getLbl_favourites() {
            return lbl_favourites;
        }

        public void setLbl_favourites(Lbl_favourites lbl_favourites) {
            this.lbl_favourites = lbl_favourites;
        }

        public Lbl_last_visited_gigs getLbl_last_visited_gigs() {
            return lbl_last_visited_gigs;
        }

        public void setLbl_last_visited_gigs(Lbl_last_visited_gigs lbl_last_visited_gigs) {
            this.lbl_last_visited_gigs = lbl_last_visited_gigs;
        }

        public Lbl_my_activity getLbl_my_activity() {
            return lbl_my_activity;
        }

        public void setLbl_my_activity(Lbl_my_activity lbl_my_activity) {
            this.lbl_my_activity = lbl_my_activity;
        }

        public Lbl_my_gigs getLbl_my_gigs() {
            return lbl_my_gigs;
        }

        public void setLbl_my_gigs(Lbl_my_gigs lbl_my_gigs) {
            this.lbl_my_gigs = lbl_my_gigs;
        }

        public Lbl_search_gigs getLbl_search_gigs() {
            return lbl_search_gigs;
        }

        public void setLbl_search_gigs(Lbl_search_gigs lbl_search_gigs) {
            this.lbl_search_gigs = lbl_search_gigs;
        }

    }


    public class Txt_choose_from_gallery {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_IBan {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_acc_name {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_acc_num {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_address_line {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_bank_addr {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_bank_name {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_buyer_needs {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_cancel_order {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_city {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_comments {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_confirm_pwd {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_deliver_gig {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_email {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_email_ {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_enter_msg {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_fullname {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_gig_cost {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_ifsc_code {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_login {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_new_pwd {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_ph_num {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_provide_info {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_pwd {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_pwd_ {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_rpwd {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_sort_code {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_suggestions {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_swift_num {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_title {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_title_gigs {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_update {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_username {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_fld_username_ {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Txt_logout_info {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Txt_take_photo {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_forgot_password {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_server_prblm {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Title_buy_services {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Title_chat {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Title_home {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Title_sell_services {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Title_buyer_request {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Title_seller_offer {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Title_settings {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_categories {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Validate_both_pwd {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_select_any_one_field {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_fld_zipcode {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_update_profile {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Err_enter_reason {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }


    public class Err_enter_stripe_details {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Err_add_stripe {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_comments {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_see_feedback {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_fld_hi {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Txt_fld_search {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Err_select_gig_image {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_fld_processing {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_fld_pending {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_fld_declined {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_fld_completed {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_fld_select_status {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_reject_order {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_activate_order {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_fld_reject_request {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Btn_buy_now {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_cart_bottom_content {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_choose_payment {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_header_title {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_item {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_product_name {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_quantity {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_select_payment_method {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_total {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_total_amount {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }

    public class Lbl_update_a_gig {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("placeholder")
        @Expose
        private String placeholder;
        @SerializedName("validation1")
        @Expose
        private String validation1;
        @SerializedName("validation2")
        @Expose
        private String validation2;
        @SerializedName("validation3")
        @Expose
        private String validation3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getValidation1() {
            return validation1;
        }

        public void setValidation1(String validation1) {
            this.validation1 = validation1;
        }

        public String getValidation2() {
            return validation2;
        }

        public void setValidation2(String validation2) {
            this.validation2 = validation2;
        }

        public String getValidation3() {
            return validation3;
        }

        public void setValidation3(String validation3) {
            this.validation3 = validation3;
        }

    }
}
