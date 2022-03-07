package ru.itis.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.service.SignService;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockModel {
    private String prevhash;
    private DataModel data;
    private String signature;
    private String ts;
    private String publickey;
    private boolean probed;

    public String[] getRowData() {

        return new String[]{
                getData().getName()
                , getData().getData()
                , getPrevhash()
                , getPublickey()
                , getSignature()
                , getTs()
                , isProbed() ? "Yes" : "No"
        };
    }

    // return as normalized JSON object
    public String toString() {
        return new StringBuilder().append("{")
                .append("\"prevhash\":\"").append(prevhash).append("\",")
                .append("\"data\":").append(data.toString()).append(",")
                .append("\"signature\":\"").append(signature).append("\",")
                .append("\"ts\":\"").append(ts).append("\",")
                .append("\"publickey\":\"").append(publickey).append("\"}")
                .toString();
    }
    public boolean getverification(){
        return SignService.verifySignature(publickey,data.toString(), signature);
    }

}
