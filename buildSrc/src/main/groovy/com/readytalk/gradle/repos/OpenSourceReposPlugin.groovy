package com.readytalk.gradle.repos

import com.readytalk.gradle.util.PluginApplicator

class OpenSourceReposPlugin extends PluginApplicator implements ReposConvention {

  void apply(Project project) {
    super.apply(project)
  }

  void addLocal() {
    def local = new LocalRepo()
    local.apply(project)
  }

  void addMainRepo() {

  }

  void addSnapshotPublishRepo() {

  }

}