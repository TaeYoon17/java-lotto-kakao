package model.entities;

import java.util.Objects;
import java.util.UUID;

/// 로또 티켓을 보증하는 바우처
/// 티켓 부스에서만 발행 가능하다.
/// 1. 코드는 객체 고유 코드
/// 2. validate 코드는 티켓 부스에서 발행한 코드를 갖는다.
public class TicketVoucher {
    UUID code;
    int validateCode;

    public TicketVoucher(UUID uuid, int validateCode) {
        this.code = uuid;
        this.validateCode = validateCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TicketVoucher that = (TicketVoucher) o;
        return validateCode == that.validateCode && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, validateCode);
    }
}
