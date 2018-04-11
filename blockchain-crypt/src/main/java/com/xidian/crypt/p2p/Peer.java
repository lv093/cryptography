package com.xidian.crypt.p2p;

import lombok.Data;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author LvLiuWei
 * @date 2018/04/11
 * 包括节点的初始化，connect/disconnect/discover,获取节点的邻节点
 */
@Data
public class Peer {

    private String ip;
    private Integer port;

    /**
     * {key} 表示相对应的节点，利用{ip:port}唯一表示
     */
    private Map<String, Peer> adjustPeers;

    /**
     * 保持一个50节点的socket连接
     * {key} 表示相对应的节点，利用{ip:port}唯一表示
     * TODO 需要进一步优化
     */
    private Map<String, Socket> adjSocket = new HashMap<>(50);

    public Peer(String ip, Integer port, Map<String, Peer> adjustPeers) {
        this.ip = ip;
        this.port = port;
        this.adjustPeers = adjustPeers;
    }

    /**
     * 创建一个与对应节点的通信socket
     * @param connector
     * @return
     */
    public Socket createSocket(Peer connector) {
        Socket socket = null;
        try {
            socket = new Socket(connector.ip, connector.port);
        } catch (IOException e) {

        }

        return socket;
    }

    public List<Peer> directPeers() {
        if (adjustPeers==null || adjustPeers.isEmpty()) {
            return new ArrayList<>(1);
        } else {
            List<Peer> directs = new ArrayList<>(adjustPeers.size());
            Iterator it = adjustPeers.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry)it.next();
                directs.add((Peer) entry.getValue());
            }
            return directs;
        }
    }

    /**
     *
     * @return
     */
    public void discoverPeers() {
        List<Peer> newPeers = null;
        //TODO 先从邻节点发现节点，如果无邻节点，则搜索根节点
        if (adjustPeers==null || adjustPeers.isEmpty()) {

        } else {
            Iterator it = adjustPeers.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Peer tempPeer = (Peer) entry.getValue();
                List<Peer> tempPeers = tempPeer.directPeers();
                for (Peer peer:tempPeers) {
                    //TODO 同时还要判断是否能够ping通
                    if (!adjustPeers.containsKey(peer.ip+":"+peer.port)) {
                        adjustPeers.put(peer.ip+":"+peer.port, peer);
                    }
                }
            }
        }

    }

    /**
     * 连接邻节点，并通过socket进行通信
     * TODO socket节点保存方式
     */
    public void connectAndSend(String info) {
        if (adjSocket.isEmpty()) {
            return;
        } else {
            Iterator it = adjSocket.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Socket socket = (Socket) entry.getValue();
                Writer writer = null;
                try {
                    writer = new OutputStreamWriter(socket.getOutputStream());
                    writer.write(info);
                } catch (IOException e) {

                } finally {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }


}
