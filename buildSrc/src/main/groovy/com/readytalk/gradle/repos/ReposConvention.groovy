package com.readytalk.gradle.repos

interface ReposConvention {
  
  void addLocal()

  void addMainRepo()

  void addSnapshotPublishRepo()

}