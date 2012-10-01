package com.readytalk.gradle.repos

import org.gradle.api.Project
import org.gradle.api.Plugin

interface ReposConvention extends Plugin<Project> {
  
  void addLocal()

  void addMainRepo()

  void addSnapshotPublishRepo()

}