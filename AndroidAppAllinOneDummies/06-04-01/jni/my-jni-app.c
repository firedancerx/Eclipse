#include <string.h>
#include <jni.h>

jstring
Java_com_allmycode_examples_ndk_MyActivity_getString
  (JNIEnv* env, jobject obj)
{
    return (*env)->NewStringUTF(env, "Hello, ");
}
