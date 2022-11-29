package com.tg.java.restpp.driver;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parse response from TigerGraph server, and return a JSONObject List.
 */
public class RestppResponse {

  private static final Logger logger = LoggerFactory.getLogger(RestppResponse.class);

  private Integer code;
  private Boolean is_error;
  private String errCode;
  private String errMsg;
  private String content;
  private List<JSONObject> results;

  /**
   * For unit test only.
   */
  public RestppResponse() {
    this.results = new ArrayList<>();
    this.code = HttpStatus.SC_OK;
    this.content = "";
  }

  public RestppResponse(HttpResponse response, Boolean panic_on_fail) throws SQLException {
    this.results = new ArrayList<>();
    this.code = HttpStatus.SC_OK;
    this.content = "";

    // Some responses have no status code, but still have entities.
    if (response.getStatusLine() == null) {
      if (panic_on_fail) {
        logger.error("Received response with no status code.");
        throw new SQLException("Received response with no status code.");
      }
    } else {
      this.code = response.getStatusLine().getStatusCode();
    }

    // Some responses' status codes are not 200, but still have entities.
    if (this.code != HttpStatus.SC_OK) {
      if (panic_on_fail) {
        logger.error("Failed to send http request: {}", String.valueOf(this.code));
        throw new SQLException("Failed to send http request: " + String.valueOf(this.code));
      }
    }

    HttpEntity entity = response.getEntity();
    if (null == entity) {
      if (panic_on_fail) {
        throw new SQLException("Received no entity.");
      } else {
        return;
      }
    }

    String content = "";
    try {
      content = EntityUtils.toString(entity);
      parse(content);
    } catch (JSONException e) {
      // Not a json, save the content directly.
      this.content = content;
    } catch (IOException e) {
      throw new SQLException(e);
    }
  }

  public void parse(String content) {
    JSONObject obj;
    obj = new JSONObject(content);
    this.is_error = obj.getBoolean("error");
    if (this.is_error) {
      this.errMsg = obj.getString("message");
      /**
       * Some queries' response do not have "error code".
       */
      if (obj.has("code")) {
        this.errCode = obj.getString("code");
      }
    } else {
      Object value = obj.get("results");
      if (value instanceof JSONObject) {
        this.results.add((JSONObject) value);
      } else if (value instanceof JSONArray) {
        JSONArray resultList = (JSONArray) value;
        for (int i = 0; i < resultList.length(); i++) {
          this.results.add(resultList.getJSONObject(i));
        }
      }
    }
  }

  public Boolean hasError() {
    return this.is_error;
  }

  public Boolean hasResultSets() {
    return (this.results.size() > 0);
  }

  public String getErrMsg() {
    return this.errMsg;
  }

  public String getErrCode() {
    return this.errCode;
  }

  public Integer getCode() {
    return this.code;
  }

  public String getContent() {
    return this.content;
  }

  public List<JSONObject> getResults() {
    return this.results;
  }

  public void addResults(List<JSONObject> results) {
    this.results.addAll(results);
  }

  /**
   * For unit test only.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < this.results.size(); i++) {
      sb.append(String.valueOf(this.results.get(i)));
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }
}
