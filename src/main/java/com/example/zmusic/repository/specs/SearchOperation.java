package com.example.zmusic.repository.specs;

public enum SearchOperation {
  /** > */
  GREATER_THAN,
  /** < */
  LESS_THAN,
  /** >= */
  GREATER_THAN_EQUAL,
  /** <= */
  LESS_THAN_EQUAL,
  /** != */
  NOT_EQUAL,
  /** == */
  EQUAL,
  /** like %string% */
  MATCH,
  /** like string% */
  MATCH_START,
  /** like %string */
  MATCH_END,
  /** in (1, 2, 3) */
  IN,
  /** not in (1, 2, 3) */
  NOT_IN
}
