package com.readytalk.gradle.dsl

import org.gradle.api.Project

class ReadyTalkClosure {
  
  private final Project project

  boolean loadProperties = true
  boolean openSource = false

  ReadyTalkClosure(Project project) {
    this.project = project
  }

  boolean isLoadProperties() {
    return loadProperties
  }

  void setLoadProperties(boolean val) {
    loadProperties = val
  }

  boolean isOpenSource() {
    return openSource
  }

  void setOpenSource(boolean val) {
    openSource = val
  }

}