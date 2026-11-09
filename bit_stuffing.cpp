#include<iostream>
using namespace std;
string sender_stuffing(string in){
    int count =0;
    string out="";
    for(char c : in){
        if(c == '0'){
            count=0;
        }else{
            count++;
        }
        out+= c;
        if(count ==5 ){
            out += '0';
            count=0;
        }
    }
    return out;
}
string reciever_stuffing(string out){
    string org="";
    int count=0;
    for(char c : out){
        if(count ==5 ){
            count=0;
            continue;
        }
        if(c == '0'){
            count=0;
        }else{
            count++;
        }
        org+= c;
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