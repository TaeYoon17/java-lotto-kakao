package model.valueobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.entities.TicketVoucher;

public class TicketVoucherBundle {
  private final List<TicketVoucher> manualVouchers;
  private final List<TicketVoucher> autoVouchers;

  public TicketVoucherBundle(List<TicketVoucher> manualVouchers, List<TicketVoucher> autoVouchers) {
    this.manualVouchers = List.copyOf(manualVouchers);
    this.autoVouchers = List.copyOf(autoVouchers);
  }

  public List<TicketVoucher> getManualVouchers() {
    return manualVouchers;
  }

  public List<TicketVoucher> getAutoVouchers() {
    return autoVouchers;
  }
}
