/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\bburd\\workspace\\03-03-12\\src\\com\\allmycode\\services\\WeatherInterface.aidl
 */
package com.allmycode.services;
public interface WeatherInterface extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.allmycode.services.WeatherInterface
{
private static final java.lang.String DESCRIPTOR = "com.allmycode.services.WeatherInterface";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.allmycode.services.WeatherInterface interface,
 * generating a proxy if needed.
 */
public static com.allmycode.services.WeatherInterface asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.allmycode.services.WeatherInterface))) {
return ((com.allmycode.services.WeatherInterface)iin);
}
return new com.allmycode.services.WeatherInterface.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_fetchWeather:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.fetchWeather(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.allmycode.services.WeatherInterface
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public java.lang.String fetchWeather(java.lang.String location) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(location);
mRemote.transact(Stub.TRANSACTION_fetchWeather, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_fetchWeather = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public java.lang.String fetchWeather(java.lang.String location) throws android.os.RemoteException;
}
