package ru.itis.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class BlockChain {

    private static Logger log = Logger.getLogger(BlockChain.class.getName());

    public List<BlockModel> bc;

    public static final List<BlockModel> chain = new ArrayList<>();

    public static final String FILE_NAME_CHAIN = System.getProperty("user.home") + File.separator + "block_chain_dis.json";

    public static void readBlockChain() {
        File file = new File(FILE_NAME_CHAIN);

        if (file.exists()) {
            log.info("read chain from file " + FILE_NAME_CHAIN);
            ObjectMapper mapper = new ObjectMapper();
            try {
                BlockChain blockChain = mapper.readValue(file, BlockChain.class);
                if (blockChain != null && blockChain.bc != null && blockChain.bc.size() > 0) {
                    chain.clear();
                    for (BlockModel b : blockChain.bc) {
                        chain.add(b);
                    }
                }
            } catch (IOException e) {
                log.log(Level.SEVERE, "load chain from file ", e);

            }
        }
    }

    public static void saveBlockChain() {
        File file = new File(FILE_NAME_CHAIN);
        log.info("save chain to file " + FILE_NAME_CHAIN);
        ObjectMapper mapper = new ObjectMapper();
        BlockChain blockChain = new BlockChain();
        blockChain.bc = chain;
        try {
            mapper.writeValue(file, blockChain);
        } catch (IOException e) {
            log.log(Level.SEVERE, "save chain to file ", e);

        }
    }
    public static List<BlockModel> fromJson(String result) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            BlockModel[] blocks = mapper.readValue(result, BlockModel[].class);
            for (BlockModel x : blocks) {
                chain.add(x);
            }
            //verify(chain);
            return chain;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }


    }


}
