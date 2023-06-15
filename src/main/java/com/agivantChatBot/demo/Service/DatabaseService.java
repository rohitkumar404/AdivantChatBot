package com.agivantChatBot.demo.Service;

import com.agivantChatBot.demo.Controller.AgentController;
import com.agivantChatBot.demo.model.FulfillmentResponse;
import com.agivantChatBot.demo.model.Message;
import com.agivantChatBot.demo.model.Messages;
import com.agivantChatBot.demo.model.Text;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DatabaseService {

    Logger logger = LoggerFactory.getLogger(AgentController.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getMaxValueIntent(Map<String,String> originalValueTagMap) {
        String groupBy = QueryUtils.getGroupByValue(originalValueTagMap);
        String operation = QueryUtils.getOperationValue(originalValueTagMap);
        String calculation = QueryUtils.getCalculationValue(originalValueTagMap);
        String data = QueryUtils.getDataValue(originalValueTagMap);

        String whereClause = "";

        if (groupBy.contains("-")) {
            String[] groupByParts = groupBy.split("-");
            groupBy = groupByParts[0];
            whereClause = groupByParts[1];
        }

        String orderByClause = operation.equalsIgnoreCase("maximum") ? "descending" : "ascending";
        String index = "";

        String queryStr;
        if (!whereClause.isEmpty()) {
            queryStr = "GET the " + operation + " " + calculation + " number of " + data + " as " + operation + data +
                    " from calldata table order by " + operation + " "  +  data + " limit 1 " + " where " + groupBy + "=" + whereClause;
            index = operation + data;
        } else {
            queryStr = "GET the " + groupBy + " with " + operation + " " + calculation + " number of " + data  +
                    " as " + operation + data + " from calldata table order by " +  orderByClause + " "  + operation +  data + " limit 1";
            index = groupBy;
        }

        logger.info("query string send to runLLM : " + queryStr);

        String query = OpenAIUtil.runLLM(queryStr);

        logger.info("sql query resturened from LLM : " + query);

        String result = runSQLQuery(query);
        String queryData = createJSONResponse(result);

        return result;
    }

    public String getAvgValueByType(Map<String,String> originalValueTagMap) {
        String groupBy = QueryUtils.getGroupByValue(originalValueTagMap);
        String operation = QueryUtils.getOperationValue(originalValueTagMap);
        String calculation = QueryUtils.getCalculationValue(originalValueTagMap);
        String data = QueryUtils.getDataValue(originalValueTagMap);

        String whereClause = "";

        operation = operation.isEmpty() ? "average" : operation;

        if (groupBy.contains("-")) {
            String[] groupByParts = groupBy.split("-");
            groupBy = groupByParts[0];
            whereClause = groupByParts[1];
        }



        String queryStr = "GET the " + groupBy + " " + operation;

        if(!Objects.equals(calculation, "")){
            queryStr = queryStr + " " + calculation + " number of ";
        }
        else{
            queryStr = queryStr + " of ";
        }

        String columnName = "";
        if(!Objects.equals(data, "")) {
            queryStr = queryStr + data + " as " + operation + data;
            columnName = operation + data;
        }
        else {
            queryStr = queryStr + " as " + operation + groupBy;
            columnName = operation + groupBy;
        }

        queryStr = queryStr + " from calldata table";

        if (!whereClause.isEmpty()) {
            queryStr += " WHERE " + groupBy + " = " + whereClause;
        }

        logger.info("query string send to runLLM : " + queryStr);

        String query = OpenAIUtil.runLLM(queryStr);

        logger.info("sql query resturened from LLM : " + query);

        String result = runSQLQuery(query);

        return result;
    }

    public String getCallCenterInfo(Map<String,String> originalValueTagMap) {
        String groupBy = QueryUtils.getGroupByValue(originalValueTagMap);
        String operation = QueryUtils.getOperationValue(originalValueTagMap);
        String calculation = QueryUtils.getCalculationValue(originalValueTagMap);
        String data = QueryUtils.getDataValue(originalValueTagMap);

        String whereClause = "";

        operation = operation.isEmpty() ? "all" : operation;

        if (groupBy.contains("-")) {
            String[] groupByParts = groupBy.split("-");
            groupBy = groupByParts[0];
            whereClause = groupByParts[1];
        }

        String queryStr = "GET the " + operation + " of unique " + groupBy;


//        if (!Objects.equals(calculation, "")){
//            queryStr = queryStr + " " + calculation + " number of ";
//        }


        String columnName = "";
        if (!Objects.equals(data, "")) {
            queryStr = queryStr + " "  +  data + " as " + operation + data;
            columnName = operation + data;
        }
        else {
            queryStr = queryStr + " as " + operation + " "  +  groupBy;
            columnName = operation + " "  +  groupBy;
        }

        queryStr = queryStr + " from calldata table";

        if (!Objects.equals(whereClause, "")){
            queryStr = queryStr + " where " + groupBy + "=" + whereClause;
        }

        logger.info("query string send to runLLM : " + queryStr);

        String query = OpenAIUtil.runLLM(queryStr);

        logger.info("sql query resturened from LLM : " + query);

        String result = runSQLQuery(query);

        return result;
    }

    public String getChannelInfo(Map<String,String> originalValueTagMap) {
        String groupBy = QueryUtils.getGroupByValue(originalValueTagMap);
        String operation = QueryUtils.getOperationValue(originalValueTagMap);
        String calculation = QueryUtils.getCalculationValue(originalValueTagMap);
        String data = QueryUtils.getDataValue(originalValueTagMap);

        String whereClause = "";

        operation = operation.isEmpty() ? "all" : operation;

        if (groupBy.contains("-")) {
            String[] groupByParts = groupBy.split("-");
            groupBy = groupByParts[0];
            whereClause = groupByParts[1];
        }

        String queryStr = "GET the " + operation + " of unique " + groupBy;


        if (!Objects.equals(calculation, "")){
            queryStr = queryStr + " " + calculation + " number of ";
        }


        String columnName = "";
        if (!Objects.equals(data, "")) {
            queryStr = queryStr + " "  +  data + " as " + operation + data;
            columnName = operation + data;
        }
        else {
            queryStr = queryStr + " as " + operation +  groupBy;
            columnName = operation + groupBy;
        }

        queryStr = queryStr + " from calldata table";

        if (!Objects.equals(whereClause, "")){
            queryStr = queryStr + " where " + groupBy + "=" + whereClause;
        }

        logger.info("query string send to runLLM : " + queryStr);

        String query = OpenAIUtil.runLLM(queryStr);
        
        logger.info("sql query resturned from LLM : " + query);

        String result = runSQLQuery(query);

        return result;
    }

    public String runSQLQuery(String query){
        RowMapper<String> stringRowMapper = (resultSet, rowNum) -> resultSet.getString(1);
        List<String> resultList = jdbcTemplate.query(query, stringRowMapper);

        if (resultList.isEmpty()) {
            return "Sorry. No Data available for this query";
        } else if (resultList.size() == 1) {
            return resultList.get(0);
        } else {
            return String.join("\n", resultList);
        }

    }

    public String returnText(List<Map<String, Object>> queryData, String index) {
        if (queryData.isEmpty()) {
            return "Sorry. No Data available for this query";
        } else if (queryData.size() == 1) {
            return queryData.get(0).get(index).toString();
        } else {
            // Create a string with new lines
            StringBuilder outputString = new StringBuilder();
            for (Map<String, Object> item : queryData) {
                outputString.append(item.get(index)).append("\n");
            }
            return outputString.toString();
        }
    }

    public String createJSONResponse(String result) {
        FulfillmentResponse response = new FulfillmentResponse();
        Messages messages = new Messages();
        Message message = new Message();
        Text text = new Text();
        List<String> textList = new ArrayList<>();

        textList.add(result);
        text.setText(textList);
        message.setText(text);
        messages.setMessages(Collections.singletonList(message));
        response.setFulfillment_response(messages);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonResponse = objectMapper.writeValueAsString(response);
            return jsonResponse;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
