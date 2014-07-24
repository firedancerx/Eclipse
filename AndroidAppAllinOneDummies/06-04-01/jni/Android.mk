LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := my-jni-app
LOCAL_SRC_FILES := my-jni-app.c
include $(BUILD_SHARED_LIBRARY)
