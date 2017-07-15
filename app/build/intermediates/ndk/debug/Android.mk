LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := printHello
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_SRC_FILES := \
	/Users/mrqiu/AndroidStudioProjects/work_studio_projects/AndroidDemo2/app/src/main/jni/main.c \

LOCAL_C_INCLUDES += /Users/mrqiu/AndroidStudioProjects/work_studio_projects/AndroidDemo2/app/src/main/jni
LOCAL_C_INCLUDES += /Users/mrqiu/AndroidStudioProjects/work_studio_projects/AndroidDemo2/app/src/debug/jni

include $(BUILD_SHARED_LIBRARY)
