#include<iostream>
#include<string.h>
using namespace std;
string sender_stuffing(string in){
    string out = "";
    string esc="01111101";
    string f="01111110";
    for ( int i=0 ; i< in.length() ; i+=8){
        string buffer = in.substr(i,8);
        if(buffer == esc || buffer == f ){
            out+=esc;
        }
        out+=buffer;
    }
    return out;
}
string reciever_stuffing(string out){
    string org="";
    string esc="01111101";
    bool flag = true;
    for ( int i=0 ; i< out.length() ; i+=8){
        string buffer = out.substr(i,8);
        
        if(buffer == esc && flag){
            flag = false;
        }
        else{
            org+=buffer;
            flag = true;
        }
    }
    return org;
}

int main(){
    cout<<"Enter origin Msg"<<endl;
    string original_msg;
    cin>>original_msg;
    cout<<"orginal  :"<<original_msg<<endl;
    cout<<"sender   :"<<sender_stuffing(original_msg)<<endl;
    cout<<"reciever :"<<reciever_stuffing(sender_stuffing(original_msg))<<endl;
}