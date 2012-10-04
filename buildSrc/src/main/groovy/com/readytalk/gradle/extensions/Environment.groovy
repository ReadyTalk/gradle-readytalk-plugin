package com.readytalk.gradle.extensions

import org.gradle.api.Project
import org.gradle.api.artifacts.repositories.ArtifactRepository

class Environment {
  private final Project project

  //private ArtifactRepository snapshotPublishRepo, stagingPublishRepo, resolveMainRepo, localRepo

  Environment(Project target) {
    this.project = target
  }
/*
  // SNAPSHOT PUBLISHER

  void setSnapshotPublishRepo(ArtifactRepository repo) {
    snapshotPublishRepo = repo
  }

  ArtifactRepository getSnapshotPublishRepo() {
    return snapshotPublishRepo
  }

  // STAGING PUBLISHER

  void setStagingPublishRepo(ArtifactRepository repo) {
    stagingPublishRepo = repo
  }

  ArtifactRepository getStagingPublishRepo() {
    return stagingPublishRepo
  }

  // LOCAL RESOLVER

  void setLocalRepo(ArtifactRepository repo) {
    localRepo = repo
  }

  ArtifactRepository getLocalRepo() {
    return localRepo
  }

  // MAIN RESOLVE REPO

  void setResolveMainRepo(ArtifactRepository repo) {
    resolveMainRepo = repo
  }

  ArtifactRepository getResolveMainRepo() {
    return resolveMainRepo
  }
  */

  void setStaging() {
    //project.repositories.add(getResolveMainRepo())
    //project.uploadArchives.repositories.add(getStagingPublishRepo())
  }

  void setCI() {
    project.version += '-SNAPSHOT'
    //project.repositories.add(getResolveMainRepo())
    //project.uploadArchives.repositories.add(getSnapshotPublishRepo())
  }

  void setDeveloper() {
    project.version += '-SNAPSHOT'
    //project.repositories.add(getLocalRepo())
    //project.repositories.add(getResolveMainRepo())
    //project.uploadArchives.repositories.add(getLocalRepo())
  }

  void determineMode() {
    if(project.has('stage')) {
      setStaging()
    } else if(project.has('ci')) {
      setCI()
    } else {
      setDeveloper()
    }
  }

}