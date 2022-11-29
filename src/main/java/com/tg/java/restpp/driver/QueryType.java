package com.tg.java.restpp.driver;

public enum QueryType {
  QUERY_TYPE_UNKNOWN,
  QUERY_TYPE_BUILTIN,
  QUERY_TYPE_INSTALLED,
  QUERY_TYPE_INTERPRETED,
  QUERY_TYPE_SCHEMA_EDGE,
  QUERY_TYPE_SCHEMA_VERTEX,
  QUERY_TYPE_SCHEMA_JOB,
  QUERY_TYPE_LOAD_JOB,
  QUERY_TYPE_GRAPH_GET_VERTEX,
  QUERY_TYPE_GRAPH_GET_EDGE,
  QUERY_TYPE_GRAPH_DELET_VERTEX,
  QUERY_TYPE_GRAPH_DELET_EDGE,
  QUERY_TYPE_GRAPH,
  QUERY_TYPE_SHORTESTPATH,
  QUERY_TYPE_ALLPATHS
}
