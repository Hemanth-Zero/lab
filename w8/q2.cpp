#include <iostream>
#include <vector>

using namespace std;
const int INF = 9999;

void BF(int n,const vector<vector<int>>& cost){
    vector<vector<int>> dist = cost;
    vector<vector<int>> nextHop(n,vector<int>(n,-1));
    for(int i = 0; i < n; ++i){
        for(int j = 0; j < n; ++j){
            if(cost[i][j] != INF && i != j){
                nextHop[i][j] = j;
            }
        }
    }
    bool updated = true;
    while(updated){
        updated = false;
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j){
                for(int k = 0; k < n; ++k){
                    if(cost[i][k] != INF && dist[k][j] != INF){
                        if(cost[i][k] + dist[k][j] < dist[i][j]){
                            dist[i][j] = cost[i][k] + dist[k][j];
                            nextHop[i][j] = nextHop[i][k];
                            updated = true;
                        }
                    }
                }
            }
        }
    }

    for(int i = 0; i < n; ++i){
        cout << "\nRouting Table for Router " << i << ":\n";
        cout << "Dest\tNextHop\tCost\n";
        for(int j = 0; j < n; ++j){
            if(i == j){
                cout << j << "\tnull\t0\n";
            }else if(dist[i][j] == INF){
                cout << j << "\tnull\tINF\n";
            }else{
                cout << j << "\t" << nextHop[i][j] << "\t" << dist[i][j] << "\n";
            }
        }
    }
}

int main(){
    int n = 4;
    vector<vector<int>> cost = {
        {0,1,4,9999},
        {1,0,2,6},
        {4,2,0,3},
        {9999,6,3,0}
    };
    int p = 6;
    vector<vector<int>> cost1 = {
        {0,4,2,9999,9999,10},
        {4,0,1,5,9999,9999},
        {2,1,0,8,10,9999},
        {9999,5,8,0,2,3},
        {9999,9999,10,2,0,1},
        {10,9999,9999,3,1,0}
    };
    BF(n,cost);
    cout << "\n";
    BF(p,cost1);
    return 0;
}