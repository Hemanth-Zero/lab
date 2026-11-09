#include <iostream>
#include <vector>
#include <queue>

using namespace std;
const int INF = 9999;

void Djkstra(int n, const vector<vector<int>>& graph){
    for(int src = 0; src < n; ++src){
        vector<int> dist(n,INF), parent(n,-1);
        priority_queue<pair<int,int>,vector<pair<int,int>>,greater<pair<int,int>>> pq;

        dist[src] = 0;
        pq.push({0, src});

        while(!pq.empty()){
            auto [d,u] = pq.top();
            pq.pop();
            if(d > dist[u]) 
                continue;
            for(int v = 0; v < n; ++v){
                if(graph[u][v] != INF && graph[u][v] != 0){
                    if(dist[u] + graph[u][v] < dist[v]){
                        dist[v] = dist[u] + graph[u][v];
                        parent[v] = u;
                        pq.push({dist[v], v});
                    }
                }
            }
        }
        cout << "\n";
        cout << "Routing Table for Router " << src << ":\n";
        cout << "Dest \t NextHop Cost\n";
        for(int dest = 0; dest < n; ++dest){
            if(dest == src){
                cout << dest << " \t null \t 0\n";
            }else{
                int nextHop = dest;
                while(parent[nextHop] != src){
                    nextHop = parent[nextHop];
                }
                cout << dest << " \t " << nextHop << " \t " << dist[dest] << "\n";
            }
        }
    }
}

int main() {
    int n = 5;
    vector<vector<int>> graph = {
        {0,2,9999,1,9999},
        {2,0,3,9999,4},
        {9999,3,0,5,6},
        {1,9999,5,0,7},
        {9999,4,6,7,0}
    };
    int p = 7;
    vector<vector<int>> graph1 = {
        {0,6,3,9999,9999,12,9999},
        {6,0,2,4,9999,9999,15},
        {3,2,0,5,7,9999,9999},
        {9999,4,5,0,1,6,9},
        {9999,9999,7,1,0,2,8},
        {12,9999,9999,6,2,0,3},
        {9999,15,9999,9,8,3,0}
    };
    Djkstra(n,graph);
    cout << "\n";
    Djkstra(p,graph1);
    return 0;
}
