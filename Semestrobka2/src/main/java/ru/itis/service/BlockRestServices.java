package ru.itis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bouncycastle.util.encoders.Hex;
import ru.itis.db.BlockChain;
import ru.itis.db.BlockModel;
import ru.itis.db.DataModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;
import static ru.itis.service.SignService.publicKey16;

public class BlockRestServices extends BlockChain{
    public static List<BlockModel> getChain() {
        try {
            URL chainUrl = new URL("http://188.93.211.195/dis/chain");
            BlockModel[] blockModels;
            HttpURLConnection conn = (HttpURLConnection) chainUrl.openConnection();
            conn.connect();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String result = br.lines().collect(Collectors.joining("\n"));

                return BlockChain.fromJson(result);
                //  System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void createBlock(DataModel dataModel) throws Exception {
        BlockModel block = new BlockModel();
        String signature = new String(Hex.encode(SignService.generateRSAPSSSignature(dataModel.toString().getBytes())));
        String prevHash = new String(Hex.encode(SignService.getHash(chain.get(chain.size() - 1))));

        block.setPrevhash(prevHash);
        block.setData(dataModel);
        block.setSignature(signature);
        block.setPublickey(publicKey16);
        sendBlockToServer(block);
    }
    public static void sendBlockToServer(BlockModel blockModel) {
        try {
            URL url = new URL("http://188.93.211.195/dis/newblock?block=" +
                    URLEncoder.encode(blockModel.toString(), "UTF-8"));

            //System.out.println(url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            int rcode = con.getResponseCode();
            if (rcode == 200) {
                ObjectMapper mapper = new ObjectMapper();
                NewBlockResponse blockResponse =
                        mapper.readValue(con.getInputStream(), NewBlockResponse.class);
                //System.out.println(blockResponse);
                getChain();
                updateDB();
            } else {
                System.out.println("response code = " + rcode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateDB(){
        DbWorker.addChainDB(chain);
    }


}
