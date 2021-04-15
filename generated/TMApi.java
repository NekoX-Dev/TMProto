package io.nekohasekai.tmicro.tmnet;

public class TMApi {

    public static int LAYER = 0;

    public static abstract class Object {

        public abstract int getConstructor();

        public void readParams(AbstractSerializedData stream, boolean exception) {
        }

        public void serializeToStream(AbstractSerializedData stream) {
        }

        public int getObjectSize() {
            SerializedData byteBuffer = new SerializedData(true);
            serializeToStream(byteBuffer);
            return byteBuffer.length();
        }
    }


    public static class Ok extends Object {

        public int getConstructor() {
            return 0x1;
        }
    }

    public static class Error extends Object {

        public int code;
        public String message;

        public Error() {
        }

        public Error(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getConstructor() {
            return 0x2;
        }

        public void readParams(AbstractSerializedData stream, boolean exception) {
            code = stream.readInt32(exception);
            message = stream.readString(exception);
        }

        public void serializeToStream(AbstractSerializedData stream) {
            stream.writeInt32(code);
            stream.writeString(message);
        }

    }

    public static abstract class Function extends Object {

        public int requestId;

    }

    public static class Response extends Object {

        public int requestId;
        public Object response;

        public Response() {
        }

        public Response(int requestId, Object response) {
            this.requestId = requestId;
            this.response = response;
        }

        public int getConstructor() {
            return 0x3;
        }

        public void readParams(AbstractSerializedData stream, boolean exception) {
            requestId = stream.readInt32(exception);
            response = TMStore.deserializeFromSteam(stream, exception);
        }

        public void serializeToStream(AbstractSerializedData stream) {
            stream.writeInt32(requestId);
            TMStore.serializeToStream(stream, response);
        }

    }

    public static class InitConnection extends Function {

        public int layer;
        public String platform;
        public String systemVersion;
        public int appVersion;
        public byte[] session;

        public InitConnection() {
        }

        public InitConnection(int layer, int appVersion, String platform, String systemVersion) {
            this.layer = layer;
            this.appVersion = appVersion;
            this.platform = platform;
            this.systemVersion = systemVersion;
        }

        public int getConstructor() {
            return 0x4;
        }

        public void readParams(AbstractSerializedData stream, boolean exception) {
            layer = stream.readInt32(exception);
            appVersion = stream.readInt32(exception);
            platform = stream.readString(exception);
            systemVersion = stream.readString(exception);
            session = stream.readByteArray(exception);
        }

        public void serializeToStream(AbstractSerializedData stream) {
            stream.writeInt32(layer);
            stream.writeInt32(appVersion);
            stream.writeString(platform);
            stream.writeString(systemVersion);
            stream.writeByteArray(session);
        }

    }

    public static class ConnInitTemp extends Object {

        public byte[] data;

        public ConnInitTemp() {
        }

        public ConnInitTemp(byte[] data) {
            this.data = data;
        }

        public int getConstructor() {
            return 0x5;
        }

        public void readParams(AbstractSerializedData stream, boolean exception) {
            data = stream.readByteArray(exception);
        }

        public void serializeToStream(AbstractSerializedData stream) {
            stream.writeByteArray(data);
        }

    }

    public static class VerifyConnection extends Function {

        public byte[] data;

        public VerifyConnection() {
        }

        public VerifyConnection(byte[] data) {
            this.data = data;
        }

        public int getConstructor() {
            return 0x6;
        }

        public void readParams(AbstractSerializedData stream, boolean exception) {
            data = stream.readByteArray(exception);

        }

        public void serializeToStream(AbstractSerializedData stream) {
            stream.writeByteArray(data);
        }

    }

    public static abstract class AuthenticationCodeType extends Object {
    }

    public static class AuthenticationCodeTypeTelegramMessage extends AuthenticationCodeType {

        public int length;

        public AuthenticationCodeTypeTelegramMessage() {
        }

        public AuthenticationCodeTypeTelegramMessage(int length) {
            this.length = length;
        }

        public int getConstructor() {
            return 0x7;
        }

        public void readParams(AbstractSerializedData stream, boolean exception) {
            length = stream.readInt32(exception);
        }

        public void serializeToStream(AbstractSerializedData stream) {
            stream.writeInt32(length);
        }

    }


    public static class AuthenticationCodeTypeSms extends AuthenticationCodeType {

        public int length;

        public AuthenticationCodeTypeSms() {
        }

        public AuthenticationCodeTypeSms(int length) {
            this.length = length;

        }

        public int getConstructor() {
            return 0x8;
        }

        public void readParams(AbstractSerializedData stream, boolean exception) {
            length = stream.readInt32(exception);
        }

        public void serializeToStream(AbstractSerializedData stream) {
            stream.writeInt32(length);
        }

    }


    public static class AuthenticationCodeTypeCall extends AuthenticationCodeType {

        public int length;

        public AuthenticationCodeTypeCall() {
        }

        public AuthenticationCodeTypeCall(int length) {

            this.length = length;

        }

        public int getConstructor() {
            return 0x9;
        }

        public void readParams(AbstractSerializedData stream, boolean exception) {
            length = stream.readInt32(exception);
        }

        public void serializeToStream(AbstractSerializedData stream) {
            stream.writeInt32(length);
        }

    }

    public static class AuthenticationCodeInfo extends Object {

        public String phoneNumber;
        public AuthenticationCodeType type;
        public AuthenticationCodeType nextType;
        public int timeout;

        public AuthenticationCodeInfo() {
        }

        public AuthenticationCodeInfo(String phoneNumber, AuthenticationCodeType type, AuthenticationCodeType nextType, int timeout) {

            this.phoneNumber = phoneNumber;
            this.type = type;
            this.nextType = nextType;
            this.timeout = timeout;

        }

        public int getConstructor() {
            return 0xa;
        }

        public void readParams(AbstractSerializedData stream, boolean exception) {
            phoneNumber = stream.readString(exception);
            type = (AuthenticationCodeType) TMStore.deserializeFromSteam(stream, exception);
            nextType = (AuthenticationCodeType) TMStore.deserializeFromSteam(stream, exception);
        }

        public void serializeToStream(AbstractSerializedData stream) {
        }
    }

    public static class EmailAddressAuthenticationCodeInfo extends Object {

        public String emailAddressPattern;
        public int length;

        public EmailAddressAuthenticationCodeInfo() {
        }

        public EmailAddressAuthenticationCodeInfo(String emailAddressPattern, int length) {

            this.emailAddressPattern = emailAddressPattern;
            this.length = length;

        }

        public int getConstructor() {
            return 0xb;
        }

        @Override
        public void readParams(AbstractSerializedData stream, boolean exception) {
            emailAddressPattern = stream.readString(exception);
            length = stream.readInt32(exception);
        }

        @Override
        public void serializeToStream(AbstractSerializedData stream) {
            stream.writeString(emailAddressPattern);
            stream.writeInt32(length);
        }
    }

    public static abstract class AuthorizationState extends Object {
    }


    public static class AuthorizationStateWaitPhoneNumber extends AuthorizationState {
        public int getConstructor() {
            return 0xc;
        }
    }


    public static class AuthorizationStateWaitCode extends AuthorizationState {

        public AuthenticationCodeInfo codeInfo;

        public int getConstructor() {
            return 0xd;
        }

        @Override
        public void readParams(AbstractSerializedData stream, boolean exception) {
            codeInfo = new AuthenticationCodeInfo();
            codeInfo.readParams(stream, exception);
        }

        @Override
        public void serializeToStream(AbstractSerializedData stream) {
            codeInfo.serializeToStream(stream);
        }

    }

    public static class AuthorizationStateWaitRegistration extends AuthorizationState {
        public int getConstructor() {
            return 0xe;
        }
    }


    public static class AuthorizationStateWaitPassword extends AuthorizationState {
        public int getConstructor() {
            return 0xf;
        }
    }

    public static abstract class Update extends Object {
    }

}