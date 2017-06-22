LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := printHello
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_SRC_FILES := \
	E:\android_work_project\AndroidDemo\app\src\main\jni\main.c \

LOCAL_C_INCLUDES += E:\android_work_project\AndroidDemo\app\src\main\jni
LOCAL_C_INCLUDES += E:\android_work_project\AndroidDemo\app\src\debug\jni

include $(BUILD_SHARED_LIBRARY)
